// https://github.com/YunaiV/ruoyi-vue-pro/tree/68645c2db02d32f13f5ef1d31fbfa7d5a81086ae/yudao-module-crm/src/main/java/cn/iocoder/yudao/module/crm/service/permission/CrmPermissionServiceImpl.java#L269-L290
public class TempClass {
    @Override
    public void deletePermissionBatch(Collection<Long> ids, Long userId) {
        List<CrmPermissionDO> permissions = permissionMapper.selectByIds(ids);
        if (CollUtil.isEmpty(permissions)) {
            throw exception(CRM_PERMISSION_NOT_EXISTS);
        }
        // 校验：数据权限的模块数据编号是一致的不可能存在两个
        if (convertSet(permissions, CrmPermissionDO::getBizId).size() > 1) {
            throw exception(CRM_PERMISSION_DELETE_FAIL);
        }
        // 校验操作人是否为负责人
        CrmPermissionDO permission = permissionMapper.selectByBizAndUserId(permissions.get(0).getBizType(), permissions.get(0).getBizId(), userId);
        if (permission == null) {
            throw exception(CRM_PERMISSION_DELETE_DENIED);
        }
        if (!CrmPermissionLevelEnum.isOwner(permission.getLevel())) {
            throw exception(CRM_PERMISSION_DELETE_DENIED);
        }

        // 删除数据权限
        permissionMapper.deleteByIds(ids);
    }

}