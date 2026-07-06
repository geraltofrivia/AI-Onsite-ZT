// https://github.com/sshahine/JFoenix/tree/a1be87d91d578e7799bc308b3b1bbe1d742a020d/jfoenix/src/main/java/com/jfoenix/skins/JFXTabPaneSkin.java#L458-L534
public class TempClass {
                @Override
                protected void layoutChildren() {
                    if (isTabsFitHeaderWidth()) {
                        updateScrollOffset(0.0);
                    } else {
                        if (!removedTabsHeaders.isEmpty()) {
                            double offset = 0;
                            double w = header.getWidth() - snapSize(rightControlButton.prefWidth(-1)) - snapSize(
                                leftControlButton.prefWidth(-1)) - snappedLeftInset() - SPACER;
                            Iterator<Node> itr = getChildren().iterator();
                            while (itr.hasNext()) {
                                Node temp = itr.next();
                                if (temp instanceof TabHeaderContainer) {
                                    TabHeaderContainer tabHeaderContainer = (TabHeaderContainer) temp;
                                    double containerPrefWidth = snapSize(tabHeaderContainer.prefWidth(-1));
                                    // if tab has been removed
                                    if (removedTabsHeaders.contains(tabHeaderContainer)) {
                                        if (offset < w) {
                                            isSelectingTab = true;
                                        }
                                        itr.remove();
                                        removedTabsHeaders.remove(tabHeaderContainer);
                                        if (removedTabsHeaders.isEmpty()) {
                                            break;
                                        }
                                    }
                                    offset += containerPrefWidth;
                                }
                            }
                        }
                    }

                    if (removedTabs) {
                        updateSelectionLine(false);
                        removedTabs = false;
                    }

                    if (isSelectingTab) {
                        // make sure the selected tab is visible
                        updateSelectionLine(true);
                        isSelectingTab = false;
                    } else {
                        // validate scroll offset
                        updateScrollOffset(scrollOffset);
                    }

                    final double tabBackgroundHeight = snapSize(prefHeight(-1));
                    final Side side = getSkinnable().getSide();
                    double tabStartX = (side == Side.LEFT || side == Side.BOTTOM) ?
                        snapSize(getWidth()) - scrollOffset : scrollOffset;
                    updateHeaderContainerClip();
                    for (Node node : getChildren()) {
                        if (node instanceof TabHeaderContainer) {
                            TabHeaderContainer tabHeaderContainer = (TabHeaderContainer) node;
                            double tabHeaderPrefWidth = snapSize(tabHeaderContainer.prefWidth(-1));
                            double tabHeaderPrefHeight = snapSize(tabHeaderContainer.prefHeight(-1));
                            tabHeaderContainer.resize(tabHeaderPrefWidth, tabHeaderPrefHeight);

                            double tabStartY = side == Side.BOTTOM ?
                                0 : tabBackgroundHeight - tabHeaderPrefHeight - snappedBottomInset();
                            if (side == Side.LEFT || side == Side.BOTTOM) {
                                // build from the right
                                tabStartX -= tabHeaderPrefWidth;
                                tabHeaderContainer.relocate(tabStartX, tabStartY);
                            } else {
                                // build from the left
                                tabHeaderContainer.relocate(tabStartX, tabStartY);
                                tabStartX += tabHeaderPrefWidth;
                            }
                        }
                    }
                    selectedTabLine.resizeRelocate((side == Side.LEFT || side == Side.BOTTOM) ?
                            snapSize(headersRegion.getWidth()) : 0
                        , tabBackgroundHeight - selectedTabLine.prefHeight(-1)
                        , snapSize(selectedTabLine.prefWidth(-1))
                        , snapSize(selectedTabLine.prefHeight(-1)));
                }

}