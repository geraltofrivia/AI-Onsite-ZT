// https://github.com/CeuiLiSA/Pixiv-Shaft/tree/95ce81191beee56f4f66fa964a7c6c8dd9d23646/app/src/main/java/ceui/lisa/view/TagItemDecoration2.java#L16-L41
public class TempClass {
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position

        if (position == 0) {
            outRect.left = spacing;
            outRect.top = spacing;
            outRect.bottom = spacing;
            outRect.right = spacing / 2;
        } else if(position == 1){
            outRect.right = spacing;
            outRect.top = spacing;
            outRect.bottom = spacing;
            outRect.left = spacing / 2;
        } else {
            if (position % 2 == 0) {
                outRect.left = spacing;
                outRect.bottom = spacing;
                outRect.right = spacing / 2;
            } else {
                outRect.left = spacing / 2;
                outRect.bottom = spacing;
                outRect.right = spacing;
            }
        }
    }

}