// https://github.com/software-mansion/react-native-svg/tree/be06e84ec4809a8071f18f9824ffbe61424ee80d/android/src/main/java/com/horcrux/svg/TSpanView.java#L117-L158
public class TempClass {
  private void drawWrappedText(Canvas canvas, Paint paint) {
    GlyphContext gc = getTextRootGlyphContext();
    pushGlyphContext();
    FontData font = gc.getFont();
    TextPaint tp = new TextPaint(paint);
    applyTextPropertiesToPaint(tp, font);
    applySpacingAndFeatures(tp, font);
    double fontSize = gc.getFontSize();

    Layout.Alignment align;
    switch (font.textAnchor) {
      default:
      case start:
        align = Layout.Alignment.ALIGN_NORMAL;
        break;

      case middle:
        align = Layout.Alignment.ALIGN_CENTER;
        break;

      case end:
        align = Layout.Alignment.ALIGN_OPPOSITE;
        break;
    }

    boolean includeFontPadding = true;
    SpannableString text = new SpannableString(mContent);
    final double width =
        PropHelper.fromRelative(mInlineSize, canvas.getWidth(), 0, mScale, fontSize);
    StaticLayout layout = getStaticLayout(tp, align, includeFontPadding, text, (int) width);

    int lineAscent = layout.getLineAscent(0);

    float dx = (float) gc.nextX(0);
    float dy = (float) (gc.nextY() + lineAscent);
    popGlyphContext();

    canvas.save();
    canvas.translate(dx, dy);
    layout.draw(canvas);
    canvas.restore();
  }

}