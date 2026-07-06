// https://github.com/CeuiLiSA/Pixiv-Shaft/tree/95ce81191beee56f4f66fa964a7c6c8dd9d23646/app/src/main/java/ceui/lisa/view/DownloadItemDecoration.java#L18-L37
public class TempClass {
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % spanCount; // item column


        if (column == 0 || column == 1) {
            outRect.top = 0;
            outRect.left = 0;
            outRect.right = spacing;
            outRect.bottom = spacing;
        }

        if (column == 3) {
            outRect.top = 0;
            outRect.left = 0;
            outRect.right = 0;
            outRect.bottom = spacing;
        }
    }

}