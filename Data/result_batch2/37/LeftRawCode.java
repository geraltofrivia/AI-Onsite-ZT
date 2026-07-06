// https://github.com/daimajia/AndroidImageSlider/tree/e318cabdef668de985efdcc45ca304e2ac6f58b5/library/src/main/java/com/daimajia/slider/library/Tricks/ViewPagerEx.java#L1287-L1305
public class TempClass {
    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }

        SavedState ss = (SavedState)state;
        super.onRestoreInstanceState(ss.getSuperState());

        if (mAdapter != null) {
            mAdapter.restoreState(ss.adapterState, ss.loader);
            setCurrentItemInternal(ss.position, false, true);
        } else {
            mRestoredCurItem = ss.position;
            mRestoredAdapterState = ss.adapterState;
            mRestoredClassLoader = ss.loader;
        }
    }

}