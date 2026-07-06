// https://github.com/PBH-BTN/PeerBanHelper/tree/f64e58a4237eda9172a9d03869852bfad512c14d/src/main/java/com/ghostchu/peerbanhelper/gui/impl/swing/mainwindow/component/TrayMenu.java#L45-L61
public class TempClass {
    private void setupSystemTray() {
        if (SystemTray.isSupported()) {
            TrayIcon icon = new TrayIcon(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/assets/icon.png")));
            icon.setImageAutoSize(true);
            SystemTray sysTray = SystemTray.getSystemTray();//获取系统托盘
            try {
                var tray = new SwingTray(icon, mouseEvent -> parent.setVisible(true), mouseEvent -> updateTrayMenus());
                sysTray.add(icon);//将托盘图表添加到系统托盘
                updateTrayMenus();
                this.swingTrayDialog = tray;
            } catch (AWTException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void minimizeToTray() {

}