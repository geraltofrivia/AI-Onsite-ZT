// https://github.com/f-droid/fdroidclient/tree/83f87606b0558083b06691ac391d41c0f52bdbb8/app/src/main/java/org/fdroid/fdroid/NotificationHelper.java#L546-L560
public class TempClass {
                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        if (errorDrawable == null) return;
                        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        final Point largeIconSize = getLargeIconSize();
                        Bitmap bitmap = Bitmap.createBitmap(largeIconSize.x, largeIconSize.y, Bitmap.Config.ARGB_8888);
                        Canvas canvas = new Canvas(bitmap);
                        errorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                        errorDrawable.draw(canvas);
                        notificationBuilder.setLargeIcon(bitmap);
                        Notification notification = notificationBuilder.build();
                        notificationManager.notify(notificationTag, notificationId, notification);
                    }

}