// https://github.com/traex/RippleEffect/tree/df5f9e4456eae8a8e98e2827a3c6f9e7185596e1/sample/src/main/java/com/andexert/rippleeffect/CustomListViewAdapter.java#L69-L88
public class TempClass {
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder;

        if (convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.row_view, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(textArrayList.get(position));

        return convertView;
    }

}