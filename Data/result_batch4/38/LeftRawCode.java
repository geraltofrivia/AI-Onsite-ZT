// https://github.com/sshahine/JFoenix/tree/a1be87d91d578e7799bc308b3b1bbe1d742a020d/jfoenix/src/main/java/com/jfoenix/skins/JFXTabPaneSkin.java#L855-L883
public class TempClass {
        private void updateSelectionLine(boolean animate) {
            double offset = 0.0;
            double selectedTabOffset = 0.0;
            double selectedTabWidth = 0.0;
            final Side side = getSkinnable().getSide();
            for (Node node : headersRegion.getChildren()) {
                if (node instanceof TabHeaderContainer) {
                    TabHeaderContainer tabHeader = (TabHeaderContainer) node;
                    double tabHeaderPrefWidth = snapSize(tabHeader.prefWidth(-1));
                    if (selectedTab != null && selectedTab.equals(tabHeader.tab)) {
                        selectedTabOffset = (side == Side.LEFT || side == Side.BOTTOM) ?
                            -offset - tabHeaderPrefWidth : offset;
                        selectedTabWidth = tabHeaderPrefWidth;
                        break;
                    }
                    offset += tabHeaderPrefWidth;
                }
            }
            // animate the tab selection
            if(selectedTabWidth > 0){
                if (animate) {
                    runTimeline(selectedTabOffset, selectedTabWidth);
                }else{
                    selectedTabLine.setTranslateX(selectedTabOffset + scrollOffset * direction);
                    scale.setX(selectedTabWidth);
                    selectedTabLineOffset = selectedTabOffset;
                }
            }
        }

}