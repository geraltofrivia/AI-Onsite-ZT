// https://github.com/zzhoujay/RichText/tree/4737a3ceada1135496441975df6371c17d3dd2c4/app/src/main/java/zhou/demo/ListViewActivity.java#L84-L110
public class TempClass {
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                Holder holder;
                if (convertView == null) {
                    convertView = LayoutInflater.from(ListViewActivity.this).inflate(R.layout.item_list, parent, false);
                    holder = new Holder(convertView);
                    convertView.setTag(holder);
                } else {
                    holder = (Holder) convertView.getTag();
                }
                Log.i("RichText", "position:" + position + ",textView:" + System.identityHashCode(holder.text));
                String text;
                if (position == 0) {
                    text = gifTest;
                } else {
                    text = testString__[position - 1];
                }
                RichText.from(text).autoPlay(true).singleLoad(false).fix(new SimpleImageFixCallback() {
                    @Override
                    public void onInit(ImageHolder holder) {
                        if (holder.isGif()) {
                            holder.setAutoFix(false);
                        }
                    }
                }).into(holder.text);
                return convertView;
            }

}