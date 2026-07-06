// https://github.com/MewX/light-novel-library_Wenku8_Android/tree/68ea337ae0e34cec1a7a27bf351e4d0465b394aa/studio-android/LightNovelLibrary/app/src/main/java/org/mewx/wenku8/reader/slider/SlidingLayout.java#L199-L217
public class TempClass {
    protected void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }

        SavedState ss = (SavedState)state;
        super.onRestoreInstanceState(ss.getSuperState());

        if (mAdapter != null) {
            mAdapter.restoreState(ss.adapterState, ss.loader);
            resetFromAdapter();
        } else {
            mRestoredAdapterState = ss.adapterState;
            mRestoredClassLoader = ss.loader;
        }
    }

    public void resetFromAdapter() {

}