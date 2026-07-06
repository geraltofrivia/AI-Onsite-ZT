// https://github.com/Bearded-Hen/Android-Bootstrap/tree/b3d62cc1847e26d420c53c92665a4fe1e6ee7ecf/AndroidBootstrap/src/main/java/com/beardedhen/androidbootstrap/BootstrapProgressBar.java#L413-L446
public class TempClass {
    private static Bitmap createRoundedBitmap(Bitmap bitmap, float cornerRadius, boolean roundRight, boolean  roundLeft) {
        Bitmap roundedBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), ARGB_8888);
        Canvas canvas = new Canvas(roundedBitmap);

        final Paint paint = new Paint();
        final Rect frame = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

//        final Rect frameLeft = new Rect(0, 0, bitmap.getWidth() /2, bitmap.getHeight());
//        final Rect frameRight = new Rect(bitmap.getWidth() /2, bitmap.getHeight(), bitmap.getWidth(), bitmap.getHeight());

        final Rect leftRect = new Rect(0, 0, bitmap.getWidth() / 2, bitmap.getHeight());
        final Rect rightRect = new Rect(bitmap.getWidth() / 2, 0, bitmap.getWidth(), bitmap.getHeight());

        // prepare canvas for transfer
        paint.setAntiAlias(true);
        paint.setColor(0xFFFFFFFF);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawARGB(0, 0, 0, 0);

        canvas.drawRoundRect(new RectF(frame), cornerRadius, cornerRadius, paint);

        if (!roundLeft){
            canvas.drawRect(leftRect, paint);
        }

        if (!roundRight){
            canvas.drawRect(rightRect, paint);
        }
        // draw bitmap
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, frame, frame, paint);

        return roundedBitmap;
    }

}