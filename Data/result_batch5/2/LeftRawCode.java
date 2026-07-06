// https://github.com/jpush/aurora-imui/tree/fb74b79288d38b8817ca24abced7bb4bc9b18c4b/Android/messagelist/src/main/java/cn/jiguang/imui/messages/ScrollMoreListener.java#L40-L82
public class TempClass {
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (dy != 0) {
            mScrolled = true;
        }
        if (mAdapter != null) {
            int lastVisibleItemPosition = 0;
            int totalItemCount = mLayoutManager.getItemCount();
            if (mLayoutManager instanceof StaggeredGridLayoutManager) {
                int[] lastVisibleItemPositions = ((StaggeredGridLayoutManager) mLayoutManager)
                        .findLastVisibleItemPositions(null);
                lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions);
            } else if (mLayoutManager instanceof LinearLayoutManager) {
                lastVisibleItemPosition = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
            } else if (mLayoutManager instanceof GridLayoutManager) {
                lastVisibleItemPosition = ((GridLayoutManager) mLayoutManager).findLastVisibleItemPosition();
            }

            if (mDisable) {
                return;
            }

            if (totalItemCount < mPreviousTotalItemCount) {
                mCurrentPage = 0;
                mPreviousTotalItemCount = totalItemCount;
                if (totalItemCount == 0) {
                    mLoading = true;
                }
            }

            if (mLoading && totalItemCount > mPreviousTotalItemCount) {
                mLoading = false;
                mPreviousTotalItemCount = totalItemCount;
            }

            int visibleThreshold = 5;
            if (!mLoading && lastVisibleItemPosition + visibleThreshold > totalItemCount) {
                mCurrentPage++;
                mAdapter.onLoadMore(mCurrentPage, totalItemCount);
                mLoading = true;
            }
        }
    }

}