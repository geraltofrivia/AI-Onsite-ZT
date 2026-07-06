// https://github.com/kaikramer/keystore-explorer/tree/fba6bbc8198c619c1d5b2386d9f818667399521b/kse/src/main/java/org/kse/gui/crypto/generalsubtree/JGeneralSubtrees.java#L315-L327
public class TempClass {
    private void removeSelectedGeneralSubtree() {
        int selectedRow = jtGeneralSubtrees.getSelectedRow();

        if (selectedRow != -1) {
            GeneralSubtree generalSubtree = (GeneralSubtree) jtGeneralSubtrees.getValueAt(selectedRow, 0);

            generalSubtrees.getGeneralSubtrees().remove(generalSubtree);

            reloadGeneralSubtreesTable();
            selectFirstGeneralSubtreeInTable();
            updateButtonControls();
        }
    }

}