// https://github.com/logisim-evolution/logisim-evolution/tree/f58928d38a792d7bc053502d4761b6b086580061/src/main/java/com/cburch/logisim/std/wiring/TunnelAttributes.java#L47-L79
public class TempClass {
  private void configureLabel() {
    Direction facing = this.facing;
    int x;
    int y;
    int halign;
    int valign;
    int margin = Tunnel.ARROW_MARGIN;
    if (facing == Direction.NORTH) {
      x = 0;
      y = margin;
      halign = TextField.H_CENTER;
      valign = TextField.V_TOP;
    } else if (facing == Direction.SOUTH) {
      x = 0;
      y = -margin;
      halign = TextField.H_CENTER;
      valign = TextField.V_BOTTOM;
    } else if (facing == Direction.EAST) {
      x = -margin;
      y = 0;
      halign = TextField.H_RIGHT;
      valign = TextField.V_CENTER_OVERALL;
    } else {
      x = margin;
      y = 0;
      halign = TextField.H_LEFT;
      valign = TextField.V_CENTER_OVERALL;
    }
    labelX = x;
    labelY = y;
    labelHAlign = halign;
    labelVAlign = valign;
  }

}