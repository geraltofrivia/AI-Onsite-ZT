// https://github.com/MewX/light-novel-library_Wenku8_Android/tree/68ea337ae0e34cec1a7a27bf351e4d0465b394aa/eclipse-android-old/LightNovelLibrary/src/com/facebook/rebound/ui/SpringConfiguratorView.java#L399-L416
public class TempClass {
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      TextView textView;
      if (convertView == null) {
        textView = new TextView(mContext);
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(params);
        int twelvePx = dpToPx(12, getResources());
        textView.setPadding(twelvePx, twelvePx, twelvePx, twelvePx);
        textView.setTextColor(mTextColor);
      } else {
        textView = (TextView) convertView;
      }
      textView.setText(mStrings.get(position));
      return textView;
    }

}