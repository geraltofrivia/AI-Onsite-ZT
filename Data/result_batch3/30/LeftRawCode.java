// https://github.com/Querz/mcaselector/tree/0accb627453cd01a9b470eaace54b9caaefc831d/src/main/java/net/querz/mcaselector/ui/component/NBTTreeView.java#L58-L73
public class TempClass {
	public void deleteSelectedItem() {
		NBTTreeItem selectedItem = (NBTTreeItem) getSelectionModel().getSelectedItem();
		if (selectedItem.getParent() == null) {
			setRoot(null);
		} else {
			NBTTreeItem oldParent = (NBTTreeItem) selectedItem.getParent();
			if (selectedItem.getValue().parent.getType() == COMPOUND) {
				((CompoundTag) selectedItem.getValue().parent).remove(selectedItem.getValue().name);
				oldParent.getChildren().remove(selectedItem);
			} else if (selectedItem.getValue().parent.getType() == LIST) {
				((ListTag) selectedItem.getValue().parent).remove(selectedItem.getValue().index);
				oldParent.getChildren().remove(selectedItem.getValue().index);
				oldParent.updateIndexes();
			}
		}
	}

}