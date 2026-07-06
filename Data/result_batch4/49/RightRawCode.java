// https://github.com/TEAMMATES/teammates/tree/5e8a38d446d8113daab924becc36206a0a86a134/src/main/java/teammates/ui/request/NotificationBasicRequest.java#L65-L74
public class TempClass {
    @Override
    public void validate() throws InvalidHttpRequestBodyException {
        assertTrue(startTimestamp > 0L, "Start timestamp should be greater than zero");
        assertTrue(endTimestamp > 0L, "End timestamp should be greater than zero");
        assertTrue(style != null, "Notification style cannot be null");
        assertTrue(targetUser != null, "Notification target user cannot be null");
        assertTrue(title != null, "Notification title cannot be null");
        assertTrue(!title.trim().isEmpty(), "Notification title cannot be empty");
        assertTrue(message != null, "Notification message cannot be null");
    }

}