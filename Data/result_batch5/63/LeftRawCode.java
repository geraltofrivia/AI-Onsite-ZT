// https://github.com/JZ-Darkal/AndroidHttpCapture/tree/e0aa4f6fcda60adf4f1c77d2ecadc6c383ba1a86/app/src/main/java/cn/darkal/networkdiagnosis/Activity/MainActivity.java#L245-L263
public class TempClass {
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (fam.isOpened()) {
            fam.close(true);
        } else if (mBackHandedFragment == null || !(mBackHandedFragment instanceof WebViewFragment)) {
            switchContent(WebViewFragment.getInstance());
        } else if (!mBackHandedFragment.onBackPressed()) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
        }
    }

}