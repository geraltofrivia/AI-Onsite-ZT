// https://github.com/JackyAndroid/AndroidTVLauncher/tree/aa20c0a53da7faedd6e20b6bb10f5933a6fdcdd6/app/src/main/java/com/jacky/launcher/adapter/AppUninstallAdapter.java#L41-L60
public class TempClass {
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_app_uninstall, null);
            holder.name = (TextView) convertView
                    .findViewById(R.id.item_app_uninstall_name);
            holder.icon = (ImageView) convertView
                    .findViewById(R.id.item_app_uninstall_iv);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        AppModel appBean = appBeanList.get(position);
        holder.icon.setBackgroundDrawable(appBean.getIcon());
        holder.name.setText(appBean.getName());
        return convertView;
    }

}