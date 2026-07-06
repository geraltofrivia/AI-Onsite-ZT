// https://github.com/qii/weiciyuan/tree/b8f4de854b12c4160a850c2217c779609c5eb445/src/org/qii/weiciyuan/ui/adapter/BrowserWeiboMsgCommentAndRepostAdapter.java#L129-L146
public class TempClass {
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null || convertView.getTag() == null) {
            convertView = initSimpleLayout(parent);
            holder = buildHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        configLayerType(holder);
        configViewFont(holder);
        bindViewData(holder, position);
        bindOnTouchListener(holder);

        return convertView;
    }

}