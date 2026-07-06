// https://github.com/zzhoujay/RichText/tree/4737a3ceada1135496441975df6371c17d3dd2c4/app/src/main/java/zhou/demo/ListViewActivity.java#L62-L120
public class TempClass {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return testString__.length + 1;
            }

            @Override
            public Object getItem(int position) {
                return testString[position];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

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

            class Holder {
                public final TextView text;

                public Holder(View view) {
                    text = (TextView) view.findViewById(R.id.text_item);
                }
            }
        });
    }

}