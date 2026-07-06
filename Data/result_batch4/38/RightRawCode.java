// https://github.com/HMCL-dev/HMCL/tree/195db2ee6d0c64a31b476530da0433bbab8b2c18/HMCL/src/main/java/org/jackhuang/hmcl/ui/construct/TabHeader.java#L358-L379
public class TempClass {
                    @Override
                    public void animateSelectionLine() {
                        double offset = 0.0D;
                        double selectedTabOffset = 0.0D;
                        double selectedTabWidth = 0.0D;

                        for (Node node : headersRegion.getChildren()) {
                            if (node instanceof TabHeaderContainer) {
                                TabHeaderContainer tabHeader = (TabHeaderContainer) node;
                                double tabHeaderPrefWidth = snapSize(tabHeader.prefWidth(-1.0D));
                                if (selectedTab != null && selectedTab.equals(tabHeader.tab)) {
                                    selectedTabOffset = offset;
                                    selectedTabWidth = tabHeaderPrefWidth;
                                    break;
                                }

                                offset += tabHeaderPrefWidth;
                            }
                        }

                        this.runTimeline(selectedTabOffset, selectedTabWidth);
                    }

}