// https://github.com/fossasia/phimpme-android/tree/60acbd2e5691d3d2f794b86e1277bfedab481035/app/src/main/java/org/fossasia/phimpme/opencamera/CameraController/CameraController2.java#L972-L1000
public class TempClass {
  @Override
  public void release() {
    if (MyDebug.LOG) Log.d(TAG, "release");
    if (thread != null) {
      thread.quitSafely();
      try {
        thread.join();
        thread = null;
        handler = null;
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    if (captureSession != null) {
      captureSession.close();
      captureSession = null;
      // pending_request_when_ready = null;
    }
    previewBuilder = null;
    if (camera != null) {
      camera.close();
      camera = null;
    }
    closePictureImageReader();
    /*if( previewImageReader != null ) {
    	previewImageReader.close();
    	previewImageReader = null;
    }*/
  }

}