// https://github.com/gephi/gephi/tree/dbc674485e70d7fbd74ef8f8489c1b7ba3dab50f/modules/DesktopSearch/src/main/java/org/gephi/desktop/search/popup/ActionPopup.java#L32-L44
public class TempClass {
    private void maybePopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            SwingUtilities.invokeLater(() -> {
                final Point p = e.getPoint();
                int row = list.locationToIndex(e.getPoint());
                list.setSelectedIndex(row);
                final JPopupMenu pop = createPopup(p);
                if (pop != null) {
                    showPopup(p.x, p.y, pop);
                }
            });
        }
    }

}