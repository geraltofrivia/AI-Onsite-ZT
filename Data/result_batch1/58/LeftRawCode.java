// https://github.com/JabRef/jabref/tree/6acae662af7a64d25c15dd23dc2f2b5e195be7ec/jabgui/src/main/java/org/jabref/gui/mergeentries/newmergedialog/cell/FieldValueCell.java#L94-L112
public class TempClass {
    private void initializeLabel() {
        label.setEditable(false);
        label.setBackground(Background.fill(Color.TRANSPARENT));
        EasyBind.subscribe(textProperty(), label::replaceText);
        label.setAutoHeight(true);
        label.setWrapText(true);
        label.setStyle("-fx-cursor: hand");

        // Workarounds
        preventTextSelectionViaMouseEvents();

        label.prefHeightProperty().bind(label.totalHeightEstimateProperty().orElseConst(-1d));

        // Fix text area consuming scroll events before they reach the outer scrollable
        label.addEventFilter(ScrollEvent.SCROLL, e -> {
            e.consume();
            FieldValueCell.this.fireEvent(e.copyFor(e.getSource(), FieldValueCell.this));
        });
    }

}