// https://github.com/CellularPrivacy/Android-IMSI-Catcher-Detector/tree/ed74bbad14f8095d564f923a4e0f6bd3d70c7859/AIMSICD/src/main/java/com/secupwn/aimsicd/data/adapter/DetectionStringAdapter.java#L20-L36
public class TempClass {
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.detection_strings_items, parent, false);
            holder = new ViewHolder(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        SmsDetectionString smsDetectionString = getItem(position);
        holder.updateDisplay(smsDetectionString);

        return convertView;
    }

}