// https://github.com/TooTallNate/Java-WebSocket/tree/8c5766a293c2dd3e0d035c0e0d70f88f57235fa8/src/main/java/org/java_websocket/framing/CloseFrame.java#L314-L332
public class TempClass {
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }

    CloseFrame that = (CloseFrame) o;

    if (code != that.code) {
      return false;
    }
    return reason != null ? reason.equals(that.reason) : that.reason == null;
  }

}