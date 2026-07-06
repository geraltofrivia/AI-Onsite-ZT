// https://github.com/Etar-Group/Etar-Calendar/tree/b428755a98ddd03e7ed87d3645faaa2486e03bf4/app/src/main/java/com/android/calendar/alerts/AlertService.java#L958-L976
public class TempClass {
    public static void createChannels(Context context) {
        if (Utils.isOreoOrLater()) {
            NotificationManager nm =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            // Create a channel per calendar (so that the user can turn it off with granularity)
            UtilsKt.createPerCalendarChannels(context, nm);

            // Create a "Background tasks" channel to keep the app alive
            NotificationChannel foregroundChannel = new NotificationChannel(
                    FOREGROUND_CHANNEL_ID,
                    context.getString(R.string.foreground_notification_channel_name),
                    NotificationManager.IMPORTANCE_LOW);
            foregroundChannel.setDescription(
                    context.getString(R.string.foreground_notification_channel_description));

            nm.createNotificationChannel(foregroundChannel);
        }
    }

}