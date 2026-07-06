// https://github.com/RipMeApp/ripme/tree/531f235154024e17e367560ddaf0232401bd537b/src/main/java/com/rarchives/ripme/ui/QueueMenuMouseListener.java#L71-L89
public class TempClass {
    @SuppressWarnings("unchecked")
    private void checkPopupTrigger(MouseEvent e) {
        if (e.getModifiersEx() == InputEvent.BUTTON3_DOWN_MASK) {
            if (!(e.getSource() instanceof JList)) {
                return;
            }

            queueList = (JList<Object>) e.getSource();
            queueListModel = (DefaultListModel<Object>) queueList.getModel();
            queueList.requestFocus();

            int nx = e.getX();

            if (nx > 500) {
                nx = nx - popup.getSize().width;
            }
            popup.show(e.getComponent(), nx, e.getY() - popup.getSize().height);
        }
    }

}