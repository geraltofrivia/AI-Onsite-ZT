// https://github.com/react-native-webrtc/react-native-webrtc/tree/0f1a265495b1d5253b34b87b927f3c7a636c17dd/android/src/main/java/com/oney/WebRTCModule/WebRTCView.java#L249-L289
public class TempClass {
    private void onFrameResolutionChanged(int videoWidth, int videoHeight, int rotation) {
        boolean changed = false;

        synchronized (layoutSyncRoot) {
            if (this.frameHeight != videoHeight) {
                this.frameHeight = videoHeight;
                changed = true;
            }
            if (this.frameRotation != rotation) {
                this.frameRotation = rotation;
                changed = true;
            }
            if (this.frameWidth != videoWidth) {
                this.frameWidth = videoWidth;
                changed = true;
            }
        }
        if (changed) {
            // The onFrameResolutionChanged method call executes on the
            // surfaceViewRenderer's render Thread.
            post(requestSurfaceViewRendererLayoutRunnable);

            // Call the onDimensionsChange callback if it's enabled
            if (onDimensionsChangeEnabled) {
                post(() -> {
                    try {
                        ReactContext reactContext = (ReactContext) getContext();
                        WritableMap params = Arguments.createMap();
                        params.putInt("width", videoWidth);
                        params.putInt("height", videoHeight);

                        // Send the event through React Native's event system
                        reactContext.getJSModule(RCTEventEmitter.class)
                                .receiveEvent(getId(), "onDimensionsChange", params);
                    } catch (Exception e) {
                        Log.e(TAG, "Error calling onDimensionsChange callback", e);
                    }
                });
            }
        }
    }

}