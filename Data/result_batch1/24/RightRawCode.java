// https://github.com/cabaletta/baritone/tree/c1aa2012b99172c033c2a72bf66e334e847890c1/src/main/java/baritone/pathing/calc/openset/BinaryHeapOpenSet.java#L92-L132
public class TempClass {
    @Override
    public final PathNode removeLowest() {
        if (size == 0) {
            throw new IllegalStateException("Cannot remove from empty heap");
        }
        PathNode result = array[1];
        PathNode val = array[size];
        array[1] = val;
        val.heapPosition = 1;
        array[size] = null;
        size--;
        result.heapPosition = -1;
        if (size < 2) {
            return result;
        }
        int index = 1;
        int smallerChild = 2;
        double cost = val.combinedCost;
        do {
            PathNode smallerChildNode = array[smallerChild];
            double smallerChildCost = smallerChildNode.combinedCost;
            if (smallerChild < size) {
                PathNode rightChildNode = array[smallerChild + 1];
                double rightChildCost = rightChildNode.combinedCost;
                if (smallerChildCost > rightChildCost) {
                    smallerChild++;
                    smallerChildCost = rightChildCost;
                    smallerChildNode = rightChildNode;
                }
            }
            if (cost <= smallerChildCost) {
                break;
            }
            array[index] = smallerChildNode;
            array[smallerChild] = val;
            val.heapPosition = smallerChild;
            smallerChildNode.heapPosition = index;
            index = smallerChild;
        } while ((smallerChild <<= 1) <= size);
        return result;
    }

}