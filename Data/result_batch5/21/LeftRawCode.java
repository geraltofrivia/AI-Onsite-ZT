// https://github.com/alibaba/vlayout/tree/00d6be0a1fa89c2f54ca923d2ea2e34d279d289e/vlayout/src/androidTest/java/com/alibaba/android/vlayout/VirtualLayoutManagerTest.java#L206-L240
public class TempClass {
    public void setRecyclerView(final RecyclerView recyclerView, boolean assignDummyPool)
            throws Throwable {
        mRecyclerView = recyclerView;
        if (assignDummyPool) {
            RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool() {
                @Override
                public RecyclerView.ViewHolder getRecycledView(int viewType) {
                    RecyclerView.ViewHolder viewHolder = super.getRecycledView(viewType);
                    if (viewHolder == null) {
                        return null;
                    }

                    ViewHolderHelper.addViewHolderFlag(viewHolder, 1); //  RecyclerView.ViewHolder.FLAG_BOUND
                    ViewHolderHelper.setField(viewHolder, "mPosition", 200);
                    ViewHolderHelper.setField(viewHolder, "mOldPosition", 300);
                    ViewHolderHelper.setField(viewHolder, "mPreLayoutPosition", 500);
                    return viewHolder;
                }

                @Override
                public void putRecycledView(RecyclerView.ViewHolder scrap) {
                    super.putRecycledView(scrap);
                }
            };
            mRecyclerView.setRecycledViewPool(pool);
        }

        // mAdapterHelper = recyclerView.mAdapterHelper;
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((ViewGroup) getActivity().findViewById(android.R.id.content)).addView(recyclerView);
            }
        });
    }

}