// https://github.com/emanuele-f/PCAPdroid/tree/017c5d5c8932396b71228317666c31234949185d/app/src/main/java/com/emanuelef/remote_capture/views/EmptyRecyclerView.java#L132-L146
public class TempClass {
    @Override
    public void setAdapter(Adapter adapter) {
        var oldAdapter = getAdapter();
        super.setAdapter(adapter);

        if (oldAdapter != null) {
            oldAdapter.unregisterAdapterDataObserver(observer);
        }

        if (adapter != null) {
            adapter.registerAdapterDataObserver(observer);
        }

        initEmptyView();
    }

}