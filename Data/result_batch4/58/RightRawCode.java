// https://github.com/mabe02/lanterna/tree/88a061dbc6c5ae74b5f64015a6619959471c680b/src/main/java/com/googlecode/lanterna/gui2/ComboBox.java#L562-L583
public class TempClass {
        public PopupWindow() {
            setHints(Arrays.asList(
                    Hint.NO_FOCUS,
                    Hint.FIXED_POSITION,
                    Hint.MENU_POPUP));
            listBox = new ActionListBox(ComboBox.this.getSize().withRows(getItemCount()));
            for(int i = 0; i < getItemCount(); i++) {
                V item = items.get(i);
                final int index = i;
                listBox.addItem(item.toString(), () -> {
                    setSelectedIndex(index, true);
                    close();
                });
            }
            listBox.setSelectedIndex(getSelectedIndex());
            TerminalSize dropDownListPreferedSize = listBox.getPreferredSize();
            if(dropDownNumberOfRows > 0) {
                listBox.setPreferredSize(dropDownListPreferedSize.withRows(
                        Math.min(dropDownNumberOfRows, dropDownListPreferedSize.getRows())));
            }
            setComponent(listBox);
        }

}