// https://github.com/jeequan/jeepay/tree/f53cb2132934823389a8c9de8a4a049f7ab1cfb0/jeepay-payment/src/main/java/com/jeequan/jeepay/pay/ctrl/refund/RefundOrderController.java#L67-L187
public class TempClass {
    public ApiRes refundOrder(){


        RefundOrder refundOrder = null;

        //获取参数 & 验签
        RefundOrderRQ rq = getRQByWithMchSign(RefundOrderRQ.class);

        try {

            if(StringUtils.isAllEmpty(rq.getMchOrderNo(), rq.getPayOrderId())){
                throw new BizException("mchOrderNo 和 payOrderId不能同时为空");
            }

            if(StringUtils.isNotEmpty(rq.getNotifyUrl()) && !StringKit.isAvailableUrl(rq.getNotifyUrl())){
                throw new BizException("异步通知地址协议仅支持http:// 或 https:// !");
            }

            PayOrder payOrder = payOrderService.queryMchOrder(rq.getMchNo(), rq.getPayOrderId(), rq.getMchOrderNo());
            if(payOrder == null){
                throw new BizException("退款订单不存在");
            }

            if(payOrder.getState() != PayOrder.STATE_SUCCESS){
                throw new BizException("订单状态不正确， 无法完成退款");
            }

            if(payOrder.getRefundState() == PayOrder.REFUND_STATE_ALL || payOrder.getRefundAmount() >= payOrder.getAmount()){
                throw new BizException("订单已全额退款，本次申请失败");
            }

            if(payOrder.getRefundAmount() + rq.getRefundAmount() > payOrder.getAmount()){
                throw new BizException("申请金额超出订单可退款余额，请检查退款金额");
            }

            if(refundOrderService.count(RefundOrder.gw().eq(RefundOrder::getPayOrderId, payOrder.getPayOrderId()).eq(RefundOrder::getState, RefundOrder.STATE_ING)) > 0){
                throw new BizException("支付订单具有在途退款申请，请稍后再试");
            }

            //全部退款金额 （退款订单表）
            Long sumSuccessRefundAmount = refundOrderService.getBaseMapper().sumSuccessRefundAmount(payOrder.getPayOrderId());
            if(sumSuccessRefundAmount >= payOrder.getAmount()){
                throw new BizException("退款单已完成全部订单退款，本次申请失败");
            }

            if(sumSuccessRefundAmount + rq.getRefundAmount() > payOrder.getAmount()){
                throw new BizException("申请金额超出订单可退款余额，请检查退款金额");
            }

            String mchNo = rq.getMchNo();
            String appId = rq.getAppId();

            // 校验退款单号是否重复
            if(refundOrderService.count(RefundOrder.gw().eq(RefundOrder::getMchNo, mchNo).eq(RefundOrder::getMchRefundNo, rq.getMchRefundNo())) > 0){
                throw new BizException("商户退款订单号["+rq.getMchRefundNo()+"]已存在");
            }

            //获取支付参数 (缓存数据) 和 商户信息
            MchAppConfigContext mchAppConfigContext = configContextQueryService.queryMchInfoAndAppInfo(mchNo, appId);
            if(mchAppConfigContext == null){
                throw new BizException("获取商户应用信息失败");
            }

            MchInfo mchInfo = mchAppConfigContext.getMchInfo();
            MchApp mchApp = mchAppConfigContext.getMchApp();

            //获取退款接口
            IRefundService refundService = SpringBeansUtil.getBean(payOrder.getIfCode() + "RefundService", IRefundService.class);
            if(refundService == null){
                throw new BizException("当前通道不支持退款！");
            }

            refundOrder = genRefundOrder(rq, payOrder, mchInfo, mchApp);

            //退款单入库 退款单状态：生成状态  此时没有和任何上游渠道产生交互。
            refundOrderService.save(refundOrder);

            // 调起退款接口
            ChannelRetMsg channelRetMsg = refundService.refund(rq, refundOrder, payOrder, mchAppConfigContext);


            //处理退款单状态
            this.processChannelMsg(channelRetMsg, refundOrder);

            RefundOrderRS bizRes = RefundOrderRS.buildByRefundOrder(refundOrder);
            return ApiRes.okWithSign(bizRes, configContextQueryService.queryMchApp(rq.getMchNo(), rq.getAppId()).getAppSecret());


        } catch (BizException e) {
            return ApiRes.customFail(e.getMessage());

        } catch (ChannelException e) {

            //处理上游返回数据
            this.processChannelMsg(e.getChannelRetMsg(), refundOrder);

            if(e.getChannelRetMsg().getChannelState() == ChannelRetMsg.ChannelState.SYS_ERROR ){
                return ApiRes.customFail(e.getMessage());
            }

            RefundOrderRS bizRes = RefundOrderRS.buildByRefundOrder(refundOrder);
            return ApiRes.okWithSign(bizRes, configContextQueryService.queryMchApp(rq.getMchNo(), rq.getAppId()).getAppSecret());


        } catch (Exception e) {
            log.error("系统异常：{}", e);
            return ApiRes.customFail("系统异常");
        }

    }

    private RefundOrder genRefundOrder(RefundOrderRQ rq, PayOrder payOrder, MchInfo mchInfo, MchApp mchApp){

        Date nowTime = new Date();
        RefundOrder refundOrder = new RefundOrder();
        refundOrder.setRefundOrderId(SeqKit.genRefundOrderId()); //退款订单号
        refundOrder.setPayOrderId(payOrder.getPayOrderId()); //支付订单号
        refundOrder.setChannelPayOrderNo(payOrder.getChannelOrderNo()); //渠道支付单号
        refundOrder.setMchNo(mchInfo.getMchNo()); //商户号
        refundOrder.setIsvNo(mchInfo.getIsvNo()); //服务商号
        refundOrder.setAppId(mchApp.getAppId()); //商户应用ID

}