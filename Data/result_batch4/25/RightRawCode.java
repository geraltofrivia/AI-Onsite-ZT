// https://github.com/PojavLauncherTeam/PojavLauncher/tree/6eb830ba7aa8465d872a9ef0a8d87592c978e292/app_pojavlauncher/src/main/java/net/kdt/pojavlaunch/multirt/RTSpinnerAdapter.java#L54-L71
public class TempClass {
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView != null?
                convertView:
                LayoutInflater.from(mContext).inflate(R.layout.item_simple_list_1, parent,false);

        Runtime runtime = mRuntimes.get(position);
        if(position == mRuntimes.size() - 1 ){
            ((TextView) view).setText(runtime.name);
        }else{
            ((TextView) view).setText(String.format("%s - %s",
                    runtime.name.replace(".tar.xz", ""),
                    runtime.versionString == null ? view.getResources().getString(R.string.multirt_runtime_corrupt) : runtime.versionString));
        }

        return view;
    }

}