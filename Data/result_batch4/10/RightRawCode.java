// https://github.com/f-droid/fdroidclient/tree/83f87606b0558083b06691ac391d41c0f52bdbb8/app/src/androidTestFull/java/org/fdroid/fdroid/nearby/LocalHTTPDManagerTest.java#L72-L110
public class TempClass {
    @Ignore
    @Test
    public void testStartStop() throws InterruptedException {
        Log.i(TAG, "testStartStop");

        final CountDownLatch startLatch = new CountDownLatch(1);
        BroadcastReceiver latchReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                startLatch.countDown();
            }
        };
        lbm.registerReceiver(latchReceiver, new IntentFilter(LocalHTTPDManager.ACTION_STARTED));
        lbm.registerReceiver(stoppedReceiver, new IntentFilter(LocalHTTPDManager.ACTION_STOPPED));
        lbm.registerReceiver(errorReceiver, new IntentFilter(LocalHTTPDManager.ACTION_ERROR));
        LocalHTTPDManager.start(context, false);
        assertTrue(startLatch.await(30, TimeUnit.SECONDS));
        assertTrue(Utils.isServerSocketInUse(PORT));
        assertTrue(Utils.canConnectToSocket(LOCALHOST, PORT));
        lbm.unregisterReceiver(latchReceiver);
        lbm.unregisterReceiver(stoppedReceiver);
        lbm.unregisterReceiver(errorReceiver);

        final CountDownLatch stopLatch = new CountDownLatch(1);
        latchReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                stopLatch.countDown();
            }
        };
        lbm.registerReceiver(startedReceiver, new IntentFilter(LocalHTTPDManager.ACTION_STARTED));
        lbm.registerReceiver(latchReceiver, new IntentFilter(LocalHTTPDManager.ACTION_STOPPED));
        lbm.registerReceiver(errorReceiver, new IntentFilter(LocalHTTPDManager.ACTION_ERROR));
        LocalHTTPDManager.stop(context);
        assertTrue(stopLatch.await(30, TimeUnit.SECONDS));
        assertFalse(Utils.isServerSocketInUse(PORT));
        assertFalse(Utils.canConnectToSocket(LOCALHOST, PORT)); // if this is flaky, just remove it
        lbm.unregisterReceiver(latchReceiver);
    }

}