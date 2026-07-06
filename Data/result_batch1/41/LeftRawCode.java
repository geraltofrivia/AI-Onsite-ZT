// https://github.com/zzhoujay/RichText/tree/4737a3ceada1135496441975df6371c17d3dd2c4/glideimagegetter/src/main/java/com/zzhoujay/glideimagegetter/ImageTarget.java#L81-L118
public class TempClass {
    @Override
    public void onLoadFailed(Exception e, Drawable errorDrawable) {
        super.onLoadFailed(e, errorDrawable);
        if (errorDrawable == null || !activityIsAlive()) {
            return;
        }
        DrawableWrapper drawableWrapper = urlDrawableWeakReference.get();
        if (drawableWrapper == null) {
            return;
        }
        holder.setImageState(ImageHolder.ImageState.FAILED);
        drawableWrapper.setDrawable(errorDrawable);
        if (rect != null) {
            drawableWrapper.setBounds(rect);
        } else {
            if (!config.autoFix && config.imageFixCallback != null) {
                config.imageFixCallback.onFailure(holder, e);
            }
            int width;
            int height = 0;
            if (config.autoFix || holder.isAutoFix() || !holder.isInvalidateSize()) {
                width = getRealWidth();
                int ow = errorDrawable.getBounds().width();
                if (ow != 0) {
                    height = errorDrawable.getBounds().height() * width / ow;
                }
                if (height == 0) {
                    height = width / 2;
                }
            } else {
                width = holder.getWidth();
                height = holder.getHeight();
            }
            drawableWrapper.setBounds(0, 0, width, height);
        }
        resetText();
        loadDone();
    }

}