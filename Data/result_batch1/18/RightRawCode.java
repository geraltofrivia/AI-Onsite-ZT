// https://github.com/k0shk0sh/FastHub/tree/fb7053da2079b624d6129ef81370a9d5774bcb9a/app/src/main/java/com/fastaccess/ui/widgets/LabelSpan.java#L125-L168
public class TempClass {
    @Override public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end,
                               float x, int top, int y, int bottom, @NonNull Paint paint) {
        final int padding = dims.getPadding();
        final int paddingW = padding + dims.getPaddingExtraWidth();
        final int maxWidth = dims.getMaxWidth();
        setupFontMetrics(text, start, end, fontMetrics, paint);
        final int bgWidth = measureWidth(txtPaint, text, start, end, false);
        fontMetrics.top = Math.min(fontMetrics.top, fontMetrics.ascent - padding);
        fontMetrics.bottom = Math.max(fontMetrics.bottom, padding);
        top = y + fontMetrics.top - fontMetrics.bottom;
        bottom = y;
        y = bottom - fontMetrics.bottom;
        final boolean isRtl = dims.isRtl();
        final int paddingAfter = dims.getPaddingAfter();
        if (txtPaint.bgColor != 0) {
            final int prevColor = txtPaint.getColor();
            final Paint.Style prevStyle = txtPaint.getStyle();
            txtPaint.setColor(txtPaint.bgColor);
            txtPaint.setStyle(Paint.Style.FILL);
            final float left;
            if (isRtl) {
                left = x + paddingAfter;
            } else {
                left = x;
            }
            final float right = left + bgWidth;
            final RectF rect = new RectF(left, top, right, bottom);
            final float radius = dims.getRoundedCornerRadius();
            canvas.drawRoundRect(rect, radius, radius, txtPaint);
            txtPaint.setColor(prevColor);
            txtPaint.setStyle(prevStyle);
        }
        if (bgWidth == maxWidth) {
            text = TextUtils.ellipsize(text.subSequence(start, end).toString(), txtPaint, bgWidth - 2 * paddingW, TextUtils.TruncateAt.MIDDLE);
            start = 0;
            end = text.length();
        }
        float textX = x + paddingW;
        if (isRtl) {
            textX += paddingAfter;
        }
        if (color != Color.TRANSPARENT) txtPaint.setColor(ViewHelper.generateTextColor(color));
        canvas.drawText(text, start, end, textX, y, txtPaint);
    }

}