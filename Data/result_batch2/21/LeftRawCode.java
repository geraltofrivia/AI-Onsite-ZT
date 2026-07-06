// https://github.com/zfdang/Android-Touch-Helper/tree/9a131ceda364e39ad283b848fdbdff7c03d54d9f/app/src/main/java/com/zfdang/touchhelper/ui/settings/SettingsFragment.java#L205-L220
public class TempClass {
                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            ViewHolder holder;
                            if (convertView == null) {
                                convertView = inflater.inflate(R.layout.layout_package_information, null);
                                holder = new ViewHolder(convertView);
                                convertView.setTag(holder);
                            } else {
                                holder = (ViewHolder) convertView.getTag();
                            }
                            AppInformation app = listApp.get(position);
                            holder.textView.setText(app.applicationName);
                            holder.imageView.setImageDrawable(app.applicationIcon);
                            holder.checkBox.setChecked(app.isChecked);
                            return convertView;
                        }

}