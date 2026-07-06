// https://github.com/yeriomin/YalpStore/tree/37f24c2fc2078cefc141d03c1379a650298ee2a8/app/src/legacy/java/android/preference/MultiSelectListPreference.java#L176-L184
public class TempClass {
    @Override
    protected Parcelable onSaveInstanceState() {
        final Parcelable superState = super.onSaveInstanceState();
        final SavedState myState = new SavedState(superState);
        myState.values = mValues;
        myState.newValues = mNewValues;
        myState.preferenceChanged = mPreferenceChanged;
        return myState;
    }

}