// https://github.com/Etar-Group/Etar-Calendar/tree/b428755a98ddd03e7ed87d3645faaa2486e03bf4/app/src/main/java/com/android/calendar/alerts/AlertService.java#L916-L946
public class TempClass {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {

            if (Utils.isOreoOrLater()) {
                createChannels(this);
                Notification notification = new NotificationCompat.Builder(this, FOREGROUND_CHANNEL_ID)
                        .setContentTitle(getString(R.string.foreground_notification_title))
                        .setSmallIcon(R.drawable.stat_notify_refresh_events)
                        .setShowWhen(false)
                        .build();
                if (Utils.isQOrLater()) {
                    int serviceType;
                    if (Utils.isUpsideDownCakeOrLater()) {
                        serviceType = FOREGROUND_SERVICE_TYPE_SYSTEM_EXEMPTED;
                    } else {
                        serviceType = 0;
                    }
                    ServiceCompat.startForeground(this, 1337, notification, serviceType);
                } else {
                    startForeground(1337, notification);
                }
            }

            Message msg = mServiceHandler.obtainMessage();
            msg.arg1 = startId;
            msg.obj = intent.getExtras();
            mServiceHandler.sendMessage(msg);
        }
        return START_REDELIVER_INTENT;
    }

}