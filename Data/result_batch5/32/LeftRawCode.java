// https://github.com/TheAlgorithms/Java/tree/24f4090210fb68b287df9c4ba37b804c6135ef04/src/main/java/com/thealgorithms/sorts/AdaptiveMergeSort.java#L24-L39
public class TempClass {
    private <T extends Comparable<T>> void merge(T[] array, T[] aux, int low, int mid, int high) {
        System.arraycopy(array, low, aux, low, high - low + 1);
        int i = low;
        int j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (i > mid) {
                array[k] = aux[j++];
            } else if (j > high) {
                array[k] = aux[i++];
            } else if (SortUtils.less(aux[j], aux[i])) {
                array[k] = aux[j++];
            } else {
                array[k] = aux[i++];
            }
        }
    }

}