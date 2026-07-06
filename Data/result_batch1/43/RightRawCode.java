// https://github.com/HMCL-dev/HMCL/tree/195db2ee6d0c64a31b476530da0433bbab8b2c18/HMCL/src/main/java/org/jackhuang/hmcl/ui/construct/TabHeader.java#L182-L208
public class TempClass {
            public HeaderContainer() {
                getStyleClass().add("tab-header-area");
                setPickOnBounds(false);

                headersRegion = new HeadersRegion();
                headersRegion.sideProperty().bind(getSkinnable().sideProperty());

                selectedTabLine = new StackPane();
                selectedTabLine.setManaged(false);
                selectedTabLine.getTransforms().addAll(scale, rotate);
                selectedTabLine.setCache(true);
                selectedTabLine.getStyleClass().addAll("tab-selected-line");
                selectedTabLine.setPrefHeight(2);
                selectedTabLine.setPrefWidth(2);
                selectedTabLine.setBackground(new Background(new BackgroundFill(ripplerColor, CornerRadii.EMPTY, Insets.EMPTY)));
                getChildren().setAll(headersRegion, selectedTabLine);
                headersRegion.setPickOnBounds(false);
                headersRegion.prefHeightProperty().bind(heightProperty());
                rotate.pivotXProperty().bind(Bindings.createDoubleBinding(() -> getSkinnable().getSide().isHorizontal() ? 0.0 : 1, getSkinnable().sideProperty()));
                rotate.pivotYProperty().bind(Bindings.createDoubleBinding(() -> getSkinnable().getSide().isHorizontal() ? 1.0 : 0, getSkinnable().sideProperty()));

                Bindings.bindContent(headersRegion.getChildren(), binding = MappedObservableList.create(getSkinnable().getTabs(), tab -> {
                    TabHeaderContainer container = new TabHeaderContainer(tab);
                    container.setVisible(true);
                    return container;
                }));
            }

}