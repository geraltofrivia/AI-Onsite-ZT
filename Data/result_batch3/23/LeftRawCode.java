// https://github.com/alibaba/Tangram-Android/tree/e8fe44d8aa0fd124afaefe945e65da3df9c9244c/tangram3/src/main/java/com/tmall/wireless/tangram3/dataparser/concrete/Card.java#L712-L730
public class TempClass {
    public void showPlaceholderView(boolean shown) {
        this.mPlaceholderRequired = shown;
        if (!shown) {
            restoreAspectRatio();
        } else {
            storeAspectRatio();
        }
        if (!this.mCells.contains(mPlaceholderCell)) {
            if (requirePlaceholderCell()) {
                this.mCells.add(mPlaceholderCell);
                notifyDataChange();
            }
        } else {
            if (!requirePlaceholderCell() && this.mCells.remove(mPlaceholderCell))
                notifyDataChange();
        }


    }

}