// https://github.com/nining377/dolby_beta/tree/258ab9b95671ecfb13a03636842890d9ca36fe75/app/src/main/java/com/raincat/dolby_beta/Hook.java#L69-L193
public class TempClass {
    public Hook(XC_LoadPackage.LoadPackageParam lpparam) {
        XposedHelpers.findAndHookMethod(XposedHelpers.findClass("com.netease.cloudmusic.NeteaseMusicApplication", lpparam.classLoader),
                "attachBaseContext", Context.class, new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        final Context context = (Context) param.thisObject;
                        final int versionCode = context.getPackageManager().getPackageInfo(PACKAGE_NAME, 0).versionCode;
                        //初始化仓库
                        ExtraHelper.init(context);
                        //初始化设置
                        SettingHelper.init(context);

                        final String processName = Tools.getCurrentProcessName(context);
                        if (processName.equals(PACKAGE_NAME)) {
                            //设置
                            new SettingHook(context, versionCode);
                            //总开关
                            if (!SettingHelper.getInstance().getSetting(SettingHelper.master_key))
                                return;
                            //音源代理
                            new ProxyHook(context, false);
                            //黑胶
                            if (SettingHelper.getInstance().isEnable(SettingHelper.black_key)) {
                                new BlackHook(context, versionCode);
                                deleteAdAndTinker();
                            }
                            //一起听
                            if (SettingHelper.getInstance().isEnable(SettingHelper.listen_key)) {
                                new ListentogetherHook(context, versionCode);

                            }
                            //不变灰
                            new GrayHook(context);
                            //自动签到
                            new AutoSignInHook(context, versionCode);
                            //去广告与去升级
                            new AdAndUpdateHook(context, versionCode);
                            //修复magisk冲突导致的无法读写外置sd卡
                            new MagiskFixHook(context);
                            //去掉内测与听歌识曲弹窗
                            new InternalDialogHook(context, versionCode);
                            //修复登录失败
                            new LoginFixHook(context);
//                            new TestHook(context);
                            ClassHelper.getCacheClassList(context, versionCode, () -> {
                                //获取账号信息
                                new UserProfileHook(context);
                                //网络访问
                                new EAPIHook(context);
                                //下载MD5校验
                                new DownloadMD5Hook(context);
                                //夜间模式
                                new NightModeHook(context, versionCode);
                                //精简tab
                                new HideTabHook(context, versionCode);
                                //精简侧边栏
                                new HideSidebarHook(context, versionCode);
                                //移除Banner
                                new HideBannerHook(context, versionCode);
                                //隐藏小红点
                                new HideBubbleHook(context);
                                //黑胶停转，隐藏K歌按钮
                                new PlayerActivityHook(context, versionCode);
                                //打开评论后优先显示最热评论
                                new CommentHotClickHook(context);
                                //绕过CDN责任链拦截器检测
                                new CdnHook(context, versionCode);
                                //广告移除增强
                                new AdExtraHook();

                                mainProcessInit = true;
                                if (mainProcessInit && playProcessInit)
                                    context.sendBroadcast(new Intent(msg_hook_play_process));
                            });
                            IntentFilter intentFilter = new IntentFilter();
                            intentFilter.addAction(msg_play_process_init_finish);
                            intentFilter.addAction(msg_send_notification);
                            context.registerReceiver(new BroadcastReceiver() {
                                @Override
                                public void onReceive(Context c, Intent intent) {
                                    if (msg_play_process_init_finish.equals(intent.getAction())) {
                                        playProcessInit = true;
                                        if (mainProcessInit && playProcessInit)
                                            context.sendBroadcast(new Intent(msg_hook_play_process));
                                    } else if (msg_send_notification.equals(intent.getAction())
                                            && SettingHelper.getInstance().isEnable(SettingHelper.warn_key)) {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                                            NotificationHelper.getInstance(context).sendUnLockNotification(context, intent.getIntExtra("code", 0x10),
                                                    intent.getStringExtra("title"), intent.getStringExtra("title"), intent.getStringExtra("message"));
                                        XposedBridge.log(intent.getStringExtra("title") + "：" + intent.getStringExtra("message"));
                                    }
                                }
                            }, intentFilter);
                        } else if (processName.equals(PACKAGE_NAME + ":play") && SettingHelper.getInstance().getSetting(SettingHelper.master_key)) {
                            //音源代理
                            new ProxyHook(context, true);
                            IntentFilter intentFilter = new IntentFilter();
                            intentFilter.addAction(msg_hook_play_process);
                            context.registerReceiver(new BroadcastReceiver() {
                                @Override
                                public void onReceive(Context c, Intent intent) {
                                    if (msg_hook_play_process.equals(intent.getAction())) {
                                        ClassHelper.getCacheClassList(context, versionCode, () -> {
                                            new EAPIHook(context);
                                            new CdnHook(context, versionCode);
                                        });
                                    }
                                }
                            }, intentFilter);
                            context.sendBroadcast(new Intent(msg_play_process_init_finish));
                        }
                    }
                });

        //关闭tinker
        Class<?> tinkerClass = XposedHelpers.findClassIfExists("com.tencent.tinker.loader.app.TinkerApplication", lpparam.classLoader);
        if (tinkerClass != null)
            XposedBridge.hookAllConstructors(tinkerClass, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    param.args[0] = 0;
                }
            });
    }

}