// https://github.com/jpush/aurora-imui/tree/fb74b79288d38b8817ca24abced7bb4bc9b18c4b/Android/chatinput/src/main/java/cn/jiguang/imui/chatinput/camera/CameraNew.java#L536-L564
public class TempClass {
    @Override
    public void release() {
        try {
            mCameraOpenCloseLock.acquire();
            closePreviewSession();
            if (mCamera != null) {
                mCamera.close();
                mCamera = null;
            }
            if (mMediaRecorder != null) {
                mMediaRecorder.release();
                mMediaRecorder = null;
            }
            if (null != mImageReader) {
                mImageReader.close();
                mImageReader = null;
            }
            if(mSurface != null){
                mSurface.release();
                mSurface = null ;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("Interrupted while trying to lock camera closing.", e);
        } finally {
            mCameraOpenCloseLock.release();
        }
        stopBackgroundThread();
    }

}