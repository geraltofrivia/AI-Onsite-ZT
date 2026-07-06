// https://github.com/Piasy/BigImageViewer/tree/067f8eb86ee522190ab473f36a1b0de11c21a663/GlideImageLoader/src/main/java/com/github/piasy/biv/loader/glide/GlideProgressSupport.java#L114-L137
public class TempClass {
        @Override
        public void update(HttpUrl url, final long bytesRead, final long contentLength) {
            String key = getRawKey(url.toString());
            final ProgressListener listener = LISTENERS.get(key);
            if (listener == null) {
                return;
            }

            Integer lastProgress = PROGRESSES.get(key);
            if (lastProgress == null) {
                // ensure `onStart` is called before `onProgress` and `onFinish`
                listener.onDownloadStart();
            }
            if (contentLength <= bytesRead) {
                listener.onDownloadFinish();
                forget(key);
                return;
            }
            int progress = (int) ((float) bytesRead / contentLength * 100);
            if (lastProgress == null || progress != lastProgress) {
                PROGRESSES.put(key, progress);
                listener.onProgress(progress);
            }
        }

}