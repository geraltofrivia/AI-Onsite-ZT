// https://github.com/TheAlgorithms/Java/tree/24f4090210fb68b287df9c4ba37b804c6135ef04/src/main/java/com/thealgorithms/datastructures/lists/CursorLinkedList.java#L75-L92
public class TempClass {
    public int indexOf(T element) {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }
        try {
            Objects.requireNonNull(element);
            Node<T> iterator = cursorSpace[head];
            for (int i = 0; i < count; i++) {
                if (iterator.element.equals(element)) {
                    return i;
                }
                iterator = cursorSpace[iterator.next];
            }
        } catch (Exception e) {
            return -1;
        }
        return -1;
    }

}