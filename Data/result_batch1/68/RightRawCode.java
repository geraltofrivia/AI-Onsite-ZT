// https://github.com/gephi/gephi/tree/dbc674485e70d7fbd74ef8f8489c1b7ba3dab50f/modules/DataLaboratoryPlugin/src/main/java/org/gephi/datalab/plugin/manipulators/ui/GeneralChooseColumnsAndRowUI.java#L123-L134
public class TempClass {
    private void refreshColumns() {
        Column[] columns = columnsAndRowChooser.getColumns();
        columnsCheckBoxes = new ColumnCheckBox[columns.length];
        contentPanel.removeAll();
        contentPanel.setLayout(new MigLayout("", "[pref!]"));
        for (int i = 0; i < columns.length; i++) {
            columnsCheckBoxes[i] = new ColumnCheckBox(columns[i], true);
            contentPanel.add(columnsCheckBoxes[i].getCheckBox(), "wrap");
        }
        contentPanel.revalidate();
        contentPanel.repaint();
    }

}