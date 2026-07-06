// https://github.com/alibaba/UltraViewPager/tree/b65233e6d63f062345229d5eef9326136bde5eed/ultraviewpager/src/main/java/com/tmall/ultraviewpager/UltraViewPagerAdapter.java#L85-L133
public class TempClass {
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int realPosition = position;
        //TODO
        if (enableLoop && adapter.getCount() != 0) {
            realPosition = position % adapter.getCount();
        }

        Object item = adapter.instantiateItem(container, realPosition);
        //TODO
        View childView = null;
        if (item instanceof View)
            childView = (View) item;
        if (item instanceof RecyclerView.ViewHolder)
            childView = ((RecyclerView.ViewHolder) item).itemView;

        int childCount = container.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = container.getChildAt(i);
            if (isViewFromObject(child, item)) {
                viewArray.put(realPosition, child);
                break;
            }
        }

        if (isEnableMultiScr()) {
            if (scrWidth == 0) {
                scrWidth = container.getResources().getDisplayMetrics().widthPixels;
            }
            RelativeLayout relativeLayout = new RelativeLayout(container.getContext());

            if (childView.getLayoutParams() != null) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                        (int) (scrWidth * multiScrRatio),
                        ViewGroup.LayoutParams.MATCH_PARENT);

                layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
                childView.setLayoutParams(layoutParams);
            }

            container.removeView(childView);
            relativeLayout.addView(childView);

            container.addView(relativeLayout);
            return relativeLayout;
        }

        return item;
    }

}