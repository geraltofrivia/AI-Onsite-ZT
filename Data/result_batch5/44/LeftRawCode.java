// https://github.com/jpush/aurora-imui/tree/fb74b79288d38b8817ca24abced7bb4bc9b18c4b/Android/chatinput/src/main/java/cn/jiguang/imui/chatinput/ChatInputView.java#L538-L725
public class TempClass {
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.aurora_pb_recordvoice_play_audio) {
            // press preview play audio button
            if (!mPlaying) {
                if (mSetData) {
                    mPreviewPlayBtn.startPlay();
                    mMediaPlayer.start();
                    mPlaying = true;
                    mChronometer.setBase(convertStrTimeToLong(mChronometer.getText().toString()));
                    mChronometer.start();
                } else {
                    playVoice();
                }
            } else {
                mSetData = true;
                mMediaPlayer.pause();
                mChronometer.stop();
                mPlaying = false;
                mPreviewPlayBtn.stopPlay();
            }

        } else if (view.getId() == R.id.aurora_btn_recordvoice_cancel) {
            // preview play audio widget cancel sending audio
            mPreviewPlayLl.setVisibility(GONE);
            mRecordContentLl.setVisibility(VISIBLE);
            mRecordVoiceBtn.cancelRecord();
            mChronometer.setText("00:00");
            if (mRecordVoiceListener != null) {
                mRecordVoiceListener.onPreviewCancel();
            }

        } else if (view.getId() == R.id.aurora_btn_recordvoice_send) {
            // preview play audio widget send audio
            mPreviewPlayLl.setVisibility(GONE);
            dismissMenuLayout();
            mRecordVoiceBtn.finishRecord();
            mChronometer.setText("00:00");
            if (mRecordVoiceListener != null) {
                mRecordVoiceListener.onPreviewSend();
            }

        } else if (view.getId() == R.id.aurora_ib_camera_full_screen) {
            // full screen/recover screen button in texture view
            if (!mIsFullScreen) {
                if (mCameraControllerListener != null) {
                    mCameraControllerListener.onFullScreenClick();
                }
                fullScreen();
            } else {
                if (mCameraControllerListener != null) {
                    mCameraControllerListener.onRecoverScreenClick();
                }
                recoverScreen();
            }

        } else if (view.getId() == R.id.aurora_ib_camera_record_video) {
            // click record video button
            // if it is not record video mode
            if (mCameraControllerListener != null) {
                mCameraControllerListener.onSwitchCameraModeClick(!mIsRecordVideoMode);
            }
            if (!mIsRecordVideoMode) {
                mIsRecordVideoMode = true;
                mCaptureBtn.setBackgroundResource(R.drawable.aurora_preview_record_video_start);
                mRecordVideoBtn.setBackgroundResource(R.drawable.aurora_preview_camera);
                fullScreen();
                mCloseBtn.setVisibility(VISIBLE);
            } else {
                mIsRecordVideoMode = false;
                mRecordVideoBtn.setBackgroundResource(R.drawable.aurora_preview_record_video);
                mCaptureBtn.setBackgroundResource(R.drawable.aurora_menuitem_send_pres);
                mFullScreenBtn.setBackgroundResource(R.drawable.aurora_preview_recover_screen);
                mFullScreenBtn.setVisibility(VISIBLE);
                mCloseBtn.setVisibility(GONE);
            }

        } else if (view.getId() == R.id.aurora_ib_camera_capture) {
            // click capture button in preview camera view
            // is record video mode
            if (mIsRecordVideoMode) {
                if (!mIsRecordingVideo) { // start recording
                    mCameraSupport.startRecordingVideo();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mCaptureBtn.setBackgroundResource(R.drawable.aurora_preview_record_video_stop);
                            mRecordVideoBtn.setVisibility(GONE);
                            mSwitchCameraBtn.setVisibility(GONE);
                            mCloseBtn.setVisibility(VISIBLE);
                        }
                    }, 200);
                    mIsRecordingVideo = true;

                } else { // finish recording
                    mVideoFilePath = mCameraSupport.finishRecordingVideo();
                    mIsRecordingVideo = false;
                    mIsRecordVideoMode = false;
                    mFinishRecordingVideo = true;
                    mCaptureBtn.setBackgroundResource(R.drawable.aurora_menuitem_send_pres);
                    mRecordVideoBtn.setVisibility(GONE);
                    mSwitchCameraBtn.setBackgroundResource(R.drawable.aurora_preview_delete_video);
                    mSwitchCameraBtn.setVisibility(VISIBLE);
                    if (mVideoFilePath != null) {
                        playVideo();
                    }
                }
                // if finished recording video, send it
            } else if (mFinishRecordingVideo) {
                if (mListener != null && mVideoFilePath != null) {
                    File file = new File(mVideoFilePath);
                    VideoItem video = new VideoItem(mVideoFilePath, file.getName(), file.length() + "",
                            System.currentTimeMillis() + "", mMediaPlayer.getDuration() / 1000);
                    List<FileItem> list = new ArrayList<>();
                    list.add(video);
                    mListener.onSendFiles(list);
                    mVideoFilePath = null;
                }
                mFinishRecordingVideo = false;
                mMediaPlayer.stop();
                mMediaPlayer.release();
                recoverScreen();
                dismissMenuLayout();
                // take picture and send it
            } else {
                mCameraSupport.takePicture();
            }
        } else if (view.getId() == R.id.aurora_ib_camera_close) {
            try {
                if (mCameraControllerListener != null) {
                    mCameraControllerListener.onCloseCameraClick();
                }
                mMediaPlayer.stop();
                mMediaPlayer.release();
                if (mCameraSupport != null) {
                    mCameraSupport.cancelRecordingVideo();
                }
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
            recoverScreen();
            dismissMenuLayout();
            mIsRecordVideoMode = false;
            mIsRecordingVideo = false;
            if (mFinishRecordingVideo) {
                mFinishRecordingVideo = false;
            }
        } else if (view.getId() == R.id.aurora_ib_camera_switch) {
            if (mFinishRecordingVideo) {
                mCameraSupport.cancelRecordingVideo();
                mSwitchCameraBtn.setBackgroundResource(R.drawable.aurora_preview_switch_camera);
                mRecordVideoBtn.setBackgroundResource(R.drawable.aurora_preview_camera);
                showRecordVideoBtn();

                mVideoFilePath = null;
                mFinishRecordingVideo = false;
                mIsRecordVideoMode = true;
                mCaptureBtn.setBackgroundResource(R.drawable.aurora_preview_record_video_start);
                mMediaPlayer.stop();
                mMediaPlayer.release();
                mCameraSupport.open(mCameraId, mWidth, mHeight, mIsBackCamera, mStyle.getCameraQuality());
            } else {
                for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
                    Camera.CameraInfo info = new Camera.CameraInfo();
                    Camera.getCameraInfo(i, info);
                    if (mIsBackCamera) {
                        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                            mCameraId = i;
                            mIsBackCamera = false;
                            mCameraSupport.release();
                            mCameraSupport.open(mCameraId, mTextureView.getWidth(), mTextureView.getHeight(),
                                    mIsBackCamera, mStyle.getCameraQuality());
                            break;
                        }
                    } else {
                        if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                            mCameraId = i;
                            mIsBackCamera = true;
                            mCameraSupport.release();
                            mCameraSupport.open(mCameraId, mTextureView.getWidth(), mTextureView.getHeight(),
                                    mIsBackCamera, mStyle.getCameraQuality());
                            break;
                        }
                    }
                }
            }
        }
    }

}