// https://github.com/jpush/aurora-imui/tree/fb74b79288d38b8817ca24abced7bb4bc9b18c4b/Android/chatinput/src/main/java/cn/jiguang/imui/chatinput/camera/CameraNew.java#L965-L1010
public class TempClass {
    @Override
    public void startRecordingVideo() {
        if (null == mCamera || !mTextureView.isAvailable() || mPreviewSize == null) {
            return;
        }
        try {
            if (mOnCameraCallbackListener != null) {
                mOnCameraCallbackListener.onStartVideoRecord();
            }
            closePreviewSession();
            setUpMediaRecorder();
            SurfaceTexture texture = mTextureView.getSurfaceTexture();
            assert texture != null;
            texture.setDefaultBufferSize(mPreviewSize.getWidth(), mPreviewSize.getHeight());
            mPreviewRequestBuilder = mCamera.createCaptureRequest(CameraDevice.TEMPLATE_RECORD);
            List<Surface> surfaces = new ArrayList<>();
            // Set up Surface for aurora_menuitem_camera preview
            Surface previewSurface = new Surface(texture);
            surfaces.add(previewSurface);
            mPreviewRequestBuilder.addTarget(previewSurface);

            // set up Surface for MediaRecorder
            Surface recorderSurface = mMediaRecorder.getSurface();
            surfaces.add(recorderSurface);
            mPreviewRequestBuilder.addTarget(recorderSurface);

            mCamera.createCaptureSession(surfaces, new CameraCaptureSession.StateCallback() {

                @Override
                public void onConfigured(CameraCaptureSession cameraCaptureSession) {
                    mCaptureSession = cameraCaptureSession;
                    updatePreview();
                    mMediaRecorder.start();
                }

                @Override
                public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
                    Toast.makeText(mContext, mContext.getString(R.string.record_video_failed),
                            Toast.LENGTH_SHORT).show();
                }
            }, mBackgroundHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

    }

}