// https://github.com/qii/weiciyuan/tree/b8f4de854b12c4160a850c2217c779609c5eb445/src/org/qii/weiciyuan/ui/adapter/CommentListAdapter.java#L49-L64
public class TempClass {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                    int totalItemCount) {
                View childView = Utility
                        .getListViewItemViewFromPosition(listView, firstVisibleItem);

                if (childView == null) {
                    return;
                }
                int position = firstVisibleItem - ((ListView) view).getHeaderViewsCount();
                if (childView.getTop() == 0 && position <= 0) {
                    topTipBar.clearAndReset();
                } else {
                    handle(position + 1);
                }
            }

}