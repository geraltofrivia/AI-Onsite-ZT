// https://github.com/FongMi/TV/tree/a03ac275211c5afa5764e9cd7724de5405f4d029/app/src/main/java/com/fongmi/android/tv/ui/custom/SpaceItemDecoration.java#L25-L42
public class TempClass {
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        if (position >= 0 && spanCount == -1) {
            outRect.left = position == 0 ? 0 : spacing / 2;
            outRect.right = position == parent.getAdapter().getItemCount() - 1 ? 0 : spacing / 2;
        } else if (position >= 0 && spanCount > 0) {
            int column = position % spanCount;
            outRect.left = column * spacing / spanCount;
            outRect.right = spacing - (column + 1) * spacing / spanCount;
            if (position >= spanCount) outRect.top = spacing;
        } else {
            outRect.left = 0;
            outRect.right = 0;
            outRect.top = 0;
            outRect.bottom = 0;
        }
    }

}