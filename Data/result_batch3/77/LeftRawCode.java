// https://github.com/rememberber/WePush/tree/2f5105fd8fea6d57e620d037a84128ae8fa8ff59/src/main/java/com/fangxuele/tool/push/ui/Init.java#L216-L289
public class TempClass {
    public static void initTray() {

        try {
            if (App.config.isUseTray() && SystemTray.isSupported()) {
                App.tray = SystemTray.getSystemTray();

                PopupMenu popupMenu = new PopupMenu();
                popupMenu.setFont(App.mainFrame.getContentPane().getFont());

                MenuItem openItem = new MenuItem("WePush");
                MenuItem exitItem = new MenuItem("Exit");

                openItem.addActionListener(e -> {
                    App.mainFrame.setExtendedState(JFrame.NORMAL);
                    App.mainFrame.setVisible(true);
                    App.mainFrame.requestFocus();
                });
                exitItem.addActionListener(e -> {
                    shutdown();
                });

                popupMenu.add(openItem);
                popupMenu.add(exitItem);

                if (SystemUtil.isWindowsOs()) {
                    App.trayIcon = new TrayIcon(UiConsts.IMAGE_LOGO_64, "WePush", popupMenu);
                } else {
                    App.trayIcon = new TrayIcon(new FlatSVGIcon("icon/icon_push.svg").getImage(), "WePush", popupMenu);
                }
                App.trayIcon.setImageAutoSize(true);

                App.trayIcon.addActionListener(e -> {
                    App.mainFrame.setExtendedState(JFrame.NORMAL);
                    App.mainFrame.setVisible(true);
                    App.mainFrame.requestFocus();
                });
                App.trayIcon.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        switch (e.getButton()) {
                            case MouseEvent.BUTTON1: {
                                App.mainFrame.setExtendedState(JFrame.NORMAL);
                                App.mainFrame.setVisible(true);
                                App.mainFrame.requestFocus();
                                break;
                            }
                            case MouseEvent.BUTTON2: {
                                logger.debug("托盘图标被鼠标中键被点击");
                                break;
                            }
                            case MouseEvent.BUTTON3: {
                                logger.debug("托盘图标被鼠标右键被点击");
                                break;
                            }
                            default: {
                                break;
                            }
                        }
                    }
                });

                try {
                    App.tray.add(App.trayIcon);
                } catch (AWTException e) {
                    e.printStackTrace();
                    logger.error(ExceptionUtils.getStackTrace(e));
                }

            }

        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
    }

}