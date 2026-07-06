// https://github.com/zo0r/react-native-push-notification/tree/fcf40a05175bb79fb5fdfe2ac31bb797bc5008bc/android/src/main/java/com/dieam/reactnativepushnotification/modules/RNPushNotificationAttributes.java#L193-L235
public class TempClass {
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(ID, id);
        bundle.putString(MESSAGE, message);
        bundle.putDouble(FIRE_DATE, fireDate);
        bundle.putString(TITLE, title);
        bundle.putString(TICKER, ticker);
        bundle.putBoolean(SHOW_WHEN, showWhen);
        bundle.putBoolean(AUTO_CANCEL, autoCancel);
        bundle.putString(LARGE_ICON, largeIcon);
        bundle.putString(LARGE_ICON_URL, largeIconUrl);
        bundle.putString(SMALL_ICON, smallIcon);
        bundle.putString(BIG_TEXT, bigText);
        bundle.putString(SUB_TEXT, subText);
        bundle.putString(BIG_PICTURE_URL, bigPictureUrl);
        bundle.putString(SHORTCUT_ID, shortcutId);
        bundle.putString(NUMBER, number);
        bundle.putString(CHANNEL_ID, channelId);
        bundle.putString(SOUND, sound);
        bundle.putString(COLOR, color);
        bundle.putString(GROUP, group);
        bundle.putBoolean(GROUP_SUMMARY, groupSummary);
        bundle.putString(MESSAGE_ID, messageId);
        bundle.putBoolean(PLAY_SOUND, playSound);
        bundle.putBoolean(VIBRATE, vibrate);
        bundle.putDouble(VIBRATION, vibration);
        bundle.putString(ACTIONS, actions);
        bundle.putBoolean(INVOKE_APP, invokeApp);
        bundle.putString(TAG, tag);
        bundle.putString(REPEAT_TYPE, repeatType);
        bundle.putDouble(REPEAT_TIME, repeatTime);
        bundle.putDouble(WHEN, when);
        bundle.putBoolean(USES_CHRONOMETER, usesChronometer);
        bundle.putDouble(TIMEOUT_AFTER, timeoutAfter);
        bundle.putBoolean(ONLY_ALERT_ONCE, onlyAlertOnce);
        bundle.putBoolean(ONGOING, ongoing);
        bundle.putString(REPLY_BUTTON_TEXT, reply_button_text);
        bundle.putString(REPLAY_PLACEHOLDER_TEXT, reply_placeholder_text);
        bundle.putBoolean(ALLOW_WHILE_IDLE, allowWhileIdle);
        bundle.putBoolean(IGNORE_IN_FOREGROUND, ignoreInForeground);
        bundle.putString(USER_INFO, userInfo);
        return bundle;
    }

}