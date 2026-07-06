// https://github.com/NeoApplications/Neo-Launcher/tree/bae394fac15c0eea0eb3d202e1272a504ee2b76b/src/com/android/launcher3/keyboard/ItemFocusIndicatorHelper.java#L121-L131
public class TempClass {
    protected void invalidateDirty() {
        if (mIsDirty) {
            mContainer.invalidate(mDirtyRect);
            mIsDirty = false;
        }

        Rect newRect = getDrawRect();
        if (newRect != null) {
            mContainer.invalidate(newRect);
        }
    }

}