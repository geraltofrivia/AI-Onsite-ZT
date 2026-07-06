// https://github.com/zo0r/react-native-push-notification/tree/fcf40a05175bb79fb5fdfe2ac31bb797bc5008bc/android/src/main/java/com/dieam/reactnativepushnotification/modules/RNReceivedMessageHandler.java#L40-L158
public class TempClass {
    public void handleReceivedMessage(RemoteMessage message) {
        String from = message.getFrom();
        RemoteMessage.Notification remoteNotification = message.getNotification();
        final Bundle bundle = new Bundle();
        // Putting it from remoteNotification first so it can be overriden if message
        // data has it
        if (remoteNotification != null) {
            // ^ It's null when message is from GCM
            RNPushNotificationConfig config = new RNPushNotificationConfig(mFirebaseMessagingService.getApplication());  

            String title = getLocalizedString(remoteNotification.getTitle(), remoteNotification.getTitleLocalizationKey(), remoteNotification.getTitleLocalizationArgs());
            String body = getLocalizedString(remoteNotification.getBody(), remoteNotification.getBodyLocalizationKey(), remoteNotification.getBodyLocalizationArgs());

            bundle.putString("title", title);
            bundle.putString("message", body);
            bundle.putString("sound", remoteNotification.getSound());
            bundle.putString("color", remoteNotification.getColor());
            bundle.putString("tag", remoteNotification.getTag());
            
            if(remoteNotification.getIcon() != null) {
              bundle.putString("smallIcon", remoteNotification.getIcon());
            } else {
              bundle.putString("smallIcon", "ic_notification");
            }
            
            if(remoteNotification.getChannelId() != null) {
              bundle.putString("channelId", remoteNotification.getChannelId());
            }
            else {
              bundle.putString("channelId", config.getNotificationDefaultChannelId());
            }

            Integer visibilty = remoteNotification.getVisibility();
            String visibilityString = "private";

            if (visibilty != null) {
                switch (visibilty) {
                    case NotificationCompat.VISIBILITY_PUBLIC:
                        visibilityString = "public";
                        break;
                    case NotificationCompat.VISIBILITY_SECRET:
                        visibilityString = "secret";
                        break;
                }
            }
          
            bundle.putString("visibility", visibilityString);

            Integer priority = remoteNotification.getNotificationPriority();
            String priorityString = "high";
            
            if (priority != null) {
              switch (priority) {
                  case NotificationCompat.PRIORITY_MAX:
                      priorityString = "max";
                      break;
                  case NotificationCompat.PRIORITY_LOW:
                      priorityString = "low";
                      break;
                  case NotificationCompat.PRIORITY_MIN:
                      priorityString = "min";
                      break;
                  case NotificationCompat.PRIORITY_DEFAULT:
                      priorityString = "default";
                      break;
              }
            }

            bundle.putString("priority", priorityString);

            Uri uri = remoteNotification.getImageUrl();

            if(uri != null) {
                String imageUrl = uri.toString();
              
                bundle.putString("bigPictureUrl", imageUrl);
                bundle.putString("largeIconUrl", imageUrl);
            }
        }

        Bundle dataBundle = new Bundle();
        Map<String, String> notificationData = message.getData();
        
        for(Map.Entry<String, String> entry : notificationData.entrySet()) {
            dataBundle.putString(entry.getKey(), entry.getValue());
        }

        bundle.putParcelable("data", dataBundle);

        Log.v(LOG_TAG, "onMessageReceived: " + bundle);

        // We need to run this on the main thread, as the React code assumes that is true.
        // Namely, DevServerHelper constructs a Handler() without a Looper, which triggers:
        // "Can't create handler inside thread that has not called Looper.prepare()"
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            public void run() {
                // Construct and load our normal React JS code bundle
                final ReactInstanceManager mReactInstanceManager = ((ReactApplication) mFirebaseMessagingService.getApplication()).getReactNativeHost().getReactInstanceManager();
                ReactContext context = mReactInstanceManager.getCurrentReactContext();
                // If it's constructed, send a notificationre
                if (context != null) {
                    handleRemotePushNotification((ReactApplicationContext) context, bundle);
                } else {
                    // Otherwise wait for construction, then send the notification
                    mReactInstanceManager.addReactInstanceEventListener(new ReactInstanceManager.ReactInstanceEventListener() {
                        public void onReactContextInitialized(ReactContext context) {
                            handleRemotePushNotification((ReactApplicationContext) context, bundle);
                            mReactInstanceManager.removeReactInstanceEventListener(this);
                        }
                    });
                    if (!mReactInstanceManager.hasStartedCreatingInitialContext()) {
                        // Construct it in the background
                        mReactInstanceManager.createReactContextInBackground();
                    }
                }
            }
        });
    }

}