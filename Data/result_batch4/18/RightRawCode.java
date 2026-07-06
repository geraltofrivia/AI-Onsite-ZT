// https://github.com/emanuele-f/PCAPdroid/tree/017c5d5c8932396b71228317666c31234949185d/app/src/main/java/com/emanuelef/remote_capture/activities/prefs/EditCtrlPermissions.java#L61-L142
public class TempClass {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(R.string.control_permissions);
        setContentView(R.layout.simple_list_activity);
        addMenuProvider(this);

        findViewById(R.id.simple_list).setFitsSystemWindows(true);
        mEmptyText = findViewById(R.id.list_empty);
        mEmptyText.setText(R.string.no_permissions_set_info);
        mListView = findViewById(R.id.listview);

        mPermissions = PCAPdroid.getInstance().getCtrlPermissions();
        mAdapter = new CtrlPermissionsAdapter(this, mPermissions);
        mListView.setAdapter(mAdapter);
        mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        mListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.list_edit_cab, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) { return false; }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                int id = item.getItemId();

                if(id == R.id.delete_entry) {
                    if(mSelected.size() >= mAdapter.getCount()) {
                        mAdapter.clear();
                        mPermissions.removeAll();
                    } else {
                        for(CtrlPermissions.Rule rule : mSelected) {
                            mAdapter.remove(rule);
                            mPermissions.remove(rule.package_name);
                        }
                    }

                    mode.finish();
                    recheckListSize();
                    return true;
                } else if(id == R.id.select_all) {
                    if(mSelected.size() >= mAdapter.getCount())
                        mode.finish();
                    else {
                        for(int i=0; i<mAdapter.getCount(); i++) {
                            if(!mListView.isItemChecked(i))
                                mListView.setItemChecked(i, true);
                        }
                    }
                }

                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                mSelected.clear();
            }

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                CtrlPermissions.Rule item = mAdapter.getItem(position);

                if(checked)
                    mSelected.add(item);
                else
                    mSelected.remove(item);

                mode.setTitle(getString(R.string.n_selected, mSelected.size()));
            }
        });
        Utils.fixListviewInsetsBottom(mListView);

        recheckListSize();
    }

}