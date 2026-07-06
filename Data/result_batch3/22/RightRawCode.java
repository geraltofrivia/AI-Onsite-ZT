// https://github.com/developersu/ns-usbloader/tree/6d243a2be1a015cfc4b6cd38bb0dd4fe47903c95/src/main/java/nsusbloader/Controllers/FontSettingsController.java#L78-L87
public class TempClass {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null)
                    return;
                Font font = Font.font(item);
                Text itemText = new Text(item);
                itemText.setFont(font);
                setGraphic(itemText);
            }

}