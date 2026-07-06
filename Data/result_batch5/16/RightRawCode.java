// https://github.com/federicoiosue/Omni-Notes/tree/a5b2ae231e35dae38a5c4274dc27672a88ddc0d5/omniNotes/src/main/java/it/feio/android/omninotes/models/views/VerticalSeekBar.java#L120-L131
public class TempClass {
  @Override
  public synchronized void setProgress(int progress) {
    if (progress >= 0) {
      super.setProgress(progress);
    } else {
      super.setProgress(0);
    }
    onSizeChanged(x, y, z, w);
    if (changeListener != null) {
      changeListener.onProgressChanged(this, progress, false);
    }
  }

}