// https://github.com/alibaba/UltraViewPager/tree/b65233e6d63f062345229d5eef9326136bde5eed/ultraviewpager/src/main/java/com/tmall/ultraviewpager/UltraViewPagerView.java#L86-L182
public class TempClass {
    protected void onMeasurePage(int widthMeasureSpec, int heightMeasureSpec) {
        if (pagerAdapter == null) {
            return;
        }
        View child = pagerAdapter.getViewAtPosition(getCurrentItem());
        if (child == null) {
            child = getChildAt(0);
        }
        if (child == null) {
            return;
        }
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if ((view.getPaddingLeft() != itemMarginLeft ||
                    view.getPaddingTop() != itemMarginTop ||
                    view.getPaddingRight() != itemMarginRight ||
                    view.getPaddingBottom() != itemMarginBottom)) {
                view.setPadding(itemMarginLeft, itemMarginTop, itemMarginRight, itemMarginBottom);
            }
        }

        ViewGroup.LayoutParams lp = child.getLayoutParams();
        final int childWidthSpec = getChildMeasureSpec(widthMeasureSpec, 0, lp.width);
        final int childHeightSpec = getChildMeasureSpec(heightMeasureSpec, 0, lp.height);

        int childWidth = (int) ((MeasureSpec.getSize(childWidthSpec) - getPaddingLeft() - getPaddingRight()) * pagerAdapter.getPageWidth(getCurrentItem()));
        int childHeight = MeasureSpec.getSize(childHeightSpec) - getPaddingTop() - getPaddingBottom();

        if (!needsMeasurePage || childWidth == 0 && childHeight == 0) {
            return;
        }

        if (!Double.isNaN(itemRatio)) {
            int itemHeight = (int) (childWidth / itemRatio);
            for (int i = 0, childCount = getChildCount(); i < childCount; i++) {
                View view = getChildAt(i);
                view.measure(MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY),
                        MeasureSpec.makeMeasureSpec(itemHeight, MeasureSpec.EXACTLY));
            }
        } else {
            for (int i = 0, childCount = getChildCount(); i < childCount; i++) {
                View view = getChildAt(i);
                if (pagerAdapter.getPageWidth(getCurrentItem()) != 1) {
                    view.measure(MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY),
                            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                } else {
                    view.measure(childWidthSpec, childHeightSpec);
                }
            }
        }

        final boolean isHorizontalScroll = scrollMode == UltraViewPager.ScrollMode.HORIZONTAL;

        childWidth = itemMarginLeft + child.getMeasuredWidth() + itemMarginRight;
        childHeight = itemMarginTop + child.getMeasuredHeight() + itemMarginBottom;

        if (!Float.isNaN(ratio)) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec((int) (getMeasuredWidth() / ratio), MeasureSpec.EXACTLY);
            setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
            for (int i = 0, childCount = getChildCount(); i < childCount; i++) {
                View view = getChildAt(i);
                view.measure(MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY), heightMeasureSpec);
            }
        } else {
            if (autoMeasureHeight) {
                if (isHorizontalScroll) {
                    constrainLength = MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY);
                    setMeasuredDimension(getMeasuredWidth(), childHeight);
                } else {
                    constrainLength = MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY);
                    setMeasuredDimension(childWidth, getMeasuredHeight());
                }

                needsMeasurePage = childHeight == itemMarginTop + itemMarginBottom;
            }
        }

        if (!pagerAdapter.isEnableMultiScr()) {
            return;
        }

        int pageLength = isHorizontalScroll ? getMeasuredWidth() : getMeasuredHeight();

        final int childLength = isHorizontalScroll ? child.getMeasuredWidth() : child.getMeasuredHeight();

        // Check that the measurement was successful
        if (childLength > 0) {
            needsMeasurePage = false;
            int difference = pageLength - childLength;
            if (getPageMargin() == 0) {
                setPageMargin(-difference);
            }
            int offscreen = (int) Math.ceil((float) pageLength / (float) childLength) + 1;
            setOffscreenPageLimit(offscreen);
            requestLayout();
        }
    }

}