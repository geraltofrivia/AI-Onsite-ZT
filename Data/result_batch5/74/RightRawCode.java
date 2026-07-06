// https://github.com/fossasia/phimpme-android/tree/60acbd2e5691d3d2f794b86e1277bfedab481035/app/src/main/java/org/fossasia/phimpme/opencamera/CameraController/CameraController2.java#L2922-L3120
public class TempClass {
  private void createCaptureSession(final MediaRecorder video_recorder)
      throws CameraControllerException {
    if (MyDebug.LOG) Log.d(TAG, "create capture session");

    if (previewBuilder == null) {
      if (MyDebug.LOG) Log.d(TAG, "previewBuilder not present!");
      throw new RuntimeException(); // throw as RuntimeException, as this is a programming error
    }
    if (camera == null) {
      if (MyDebug.LOG) Log.e(TAG, "no camera");
      return;
    }

    if (captureSession != null) {
      if (MyDebug.LOG) Log.d(TAG, "close old capture session");
      captureSession.close();
      captureSession = null;
      // pending_request_when_ready = null;
    }

    try {
      if (video_recorder != null) {
        closePictureImageReader();
      } else {
        // in some cases need to recreate picture imageReader and the texture default buffer size
        // (e.g., see test testTakePhotoPreviewPaused())
        createPictureImageReader();
      }
      if (texture != null) {
        // need to set the texture size
        if (MyDebug.LOG) Log.d(TAG, "set size of preview texture");
        if (preview_width == 0 || preview_height == 0) {
          if (MyDebug.LOG) Log.e(TAG, "application needs to call setPreviewSize()");
          throw new RuntimeException(); // throw as RuntimeException, as this is a programming error
        }
        texture.setDefaultBufferSize(preview_width, preview_height);
        // also need to create a new surface for the texture, in case the size has changed - but
        // make sure we remove the old one first!
        if (surface_texture != null) {
          if (MyDebug.LOG) Log.d(TAG, "remove old target: " + surface_texture);
          previewBuilder.removeTarget(surface_texture);
        }
        this.surface_texture = new Surface(texture);
        if (MyDebug.LOG) Log.d(TAG, "created new target: " + surface_texture);
      }
      if (video_recorder != null) {
        if (MyDebug.LOG) Log.d(TAG, "creating capture session for video recording");
      } else {
        if (MyDebug.LOG)
          Log.d(TAG, "picture size: " + imageReader.getWidth() + " x " + imageReader.getHeight());
      }
      /*if( MyDebug.LOG )
      Log.d(TAG, "preview size: " + previewImageReader.getWidth() + " x " + previewImageReader.getHeight());*/
      if (MyDebug.LOG)
        Log.d(TAG, "preview size: " + this.preview_width + " x " + this.preview_height);

      class MyStateCallback extends CameraCaptureSession.StateCallback {
        private boolean callback_done; // must sychronize on this and notifyAll when setting to true

        @Override
        public void onConfigured(@NonNull CameraCaptureSession session) {
          if (MyDebug.LOG) {
            Log.d(TAG, "onConfigured: " + session);
            Log.d(TAG, "captureSession was: " + captureSession);
          }
          if (camera == null) {
            if (MyDebug.LOG) {
              Log.d(TAG, "camera is closed");
            }
            synchronized (create_capture_session_lock) {
              callback_done = true;
              create_capture_session_lock.notifyAll();
            }
            return;
          }
          captureSession = session;
          Surface surface = getPreviewSurface();
          previewBuilder.addTarget(surface);
          if (video_recorder != null) previewBuilder.addTarget(video_recorder.getSurface());
          try {
            setRepeatingRequest();
          } catch (CameraAccessException e) {
            if (MyDebug.LOG) {
              Log.e(TAG, "failed to start preview");
              Log.e(TAG, "reason: " + e.getReason());
              Log.e(TAG, "message: " + e.getMessage());
            }
            e.printStackTrace();
            // we indicate that we failed to start the preview by setting captureSession back to
            // null
            // this will cause a CameraControllerException to be thrown below
            captureSession = null;
          }
          synchronized (create_capture_session_lock) {
            callback_done = true;
            create_capture_session_lock.notifyAll();
          }
        }

        @Override
        public void onConfigureFailed(@NonNull CameraCaptureSession session) {
          if (MyDebug.LOG) {
            Log.d(TAG, "onConfigureFailed: " + session);
            Log.d(TAG, "captureSession was: " + captureSession);
          }
          synchronized (create_capture_session_lock) {
            callback_done = true;
            create_capture_session_lock.notifyAll();
          }
          // don't throw CameraControllerException here, as won't be caught - instead we throw
          // CameraControllerException below
        }

        /*@Override
        public void onReady(CameraCaptureSession session) {
        	if( MyDebug.LOG )
        		Log.d(TAG, "onReady: " + session);
        	if( pending_request_when_ready != null ) {
        		if( MyDebug.LOG )
        			Log.d(TAG, "have pending_request_when_ready: " + pending_request_when_ready);
        		CaptureRequest request = pending_request_when_ready;
        		pending_request_when_ready = null;
        		try {
        			captureSession.capture(request, previewCaptureCallback, handler);
        		}
        		catch(CameraAccessException e) {
        			if( MyDebug.LOG ) {
        				Log.e(TAG, "failed to take picture");
        				Log.e(TAG, "reason: " + e.getReason());
        				Log.e(TAG, "message: " + e.getMessage());
        			}
        			e.printStackTrace();
        			jpeg_cb = null;
        			if( take_picture_error_cb != null ) {
        				take_picture_error_cb.onError();
        				take_picture_error_cb = null;
        			}
        		}
        	}
        }*/
      }
      final MyStateCallback myStateCallback = new MyStateCallback();

      Surface preview_surface = getPreviewSurface();
      List<Surface> surfaces;
      if (video_recorder != null) {
        surfaces = Arrays.asList(preview_surface, video_recorder.getSurface());
      } else if (imageReaderRaw != null) {
        surfaces =
            Arrays.asList(preview_surface, imageReader.getSurface(), imageReaderRaw.getSurface());
      } else {
        surfaces = Arrays.asList(preview_surface, imageReader.getSurface());
      }
      if (MyDebug.LOG) {
        Log.d(TAG, "texture: " + texture);
        Log.d(TAG, "preview_surface: " + preview_surface);
        if (video_recorder == null) {
          if (imageReaderRaw != null) {
            Log.d(TAG, "imageReaderRaw: " + imageReaderRaw);
            Log.d(TAG, "imageReaderRaw: " + imageReaderRaw.getWidth());
            Log.d(TAG, "imageReaderRaw: " + imageReaderRaw.getHeight());
            Log.d(TAG, "imageReaderRaw: " + imageReaderRaw.getImageFormat());
          } else {
            Log.d(TAG, "imageReader: " + imageReader);
            Log.d(TAG, "imageReader: " + imageReader.getWidth());
            Log.d(TAG, "imageReader: " + imageReader.getHeight());
            Log.d(TAG, "imageReader: " + imageReader.getImageFormat());
          }
        }
      }
      camera.createCaptureSession(surfaces, myStateCallback, handler);
      if (MyDebug.LOG) Log.d(TAG, "wait until session created...");
      synchronized (create_capture_session_lock) {
        while (!myStateCallback.callback_done) {
          try {
            // release the lock, and wait until myStateCallback calls notifyAll()
            create_capture_session_lock.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
      if (MyDebug.LOG) {
        Log.d(TAG, "created captureSession: " + captureSession);
      }
      if (captureSession == null) {
        if (MyDebug.LOG) Log.e(TAG, "failed to create capture session");
        throw new CameraControllerException();
      }
    } catch (CameraAccessException e) {
      if (MyDebug.LOG) {
        Log.e(TAG, "CameraAccessException trying to create capture session");
        Log.e(TAG, "reason: " + e.getReason());
        Log.e(TAG, "message: " + e.getMessage());
      }
      e.printStackTrace();
      throw new CameraControllerException();
    }
  }

}