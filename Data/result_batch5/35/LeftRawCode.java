// https://github.com/software-mansion/react-native-svg/tree/be06e84ec4809a8071f18f9824ffbe61424ee80d/android/src/main/java/com/horcrux/svg/TSpanView.java#L84-L115
public class TempClass {
  @Override
  void draw(Canvas canvas, Paint paint, float opacity) {
    if (mContent != null) {
      if (mInlineSize != null && mInlineSize.value != 0) {
        if (setupFillPaint(paint, opacity * fillOpacity)) {
          drawWrappedText(canvas, paint);
        }
        if (setupStrokePaint(paint, opacity * strokeOpacity)) {
          drawWrappedText(canvas, paint);
        }
      } else {
        int numEmoji = emoji.size();
        if (numEmoji > 0) {
          GlyphContext gc = getTextRootGlyphContext();
          FontData font = gc.getFont();
          applyTextPropertiesToPaint(paint, font);
          for (int i = 0; i < numEmoji; i++) {
            String current = emoji.get(i);
            Matrix mid = emojiTransforms.get(i);
            canvas.save();
            canvas.concat(mid);
            canvas.drawText(current, 0, 0, paint);
            canvas.restore();
          }
        }
        drawPath(canvas, paint, opacity);
      }
    } else {
      clip(canvas, paint);
      drawGroup(canvas, paint, opacity);
    }
  }

}