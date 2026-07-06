// https://github.com/AntennaPod/AntennaPod/tree/b9014b64e05510322e1d9a79c5ab2f8df62c8904/app/src/main/java/de/danoeh/antennapod/ui/screen/playback/audio/ChapterSeekBar.java#L78-L93
public class TempClass {
    @Override
    protected synchronized void onDraw(Canvas canvas) {
        center = (getBottom() - getPaddingBottom() - getTop() - getPaddingTop()) / 2.0f;
        top = center - density * 1.5f;
        bottom = center + density * 1.5f;
        width = (float) (getRight() - getPaddingRight() - getLeft() - getPaddingLeft());
        progressSecondary = getSecondaryProgress() / (float) getMax() * width;
        progressPrimary = getProgress() / (float) getMax() * width;

        if (dividerPos == null) {
            drawProgress(canvas);
        } else {
            drawProgressChapters(canvas);
        }
        drawThumb(canvas);
    }

}