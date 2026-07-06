// https://github.com/TheAlgorithms/Java/tree/24f4090210fb68b287df9c4ba37b804c6135ef04/src/main/java/com/thealgorithms/sorts/TimSort.java#L34-L50
public class TempClass {
    private <T extends Comparable<T>> void merge(T[] a, final int lo, final int mid, final int hi) {
        int i = lo;
        int j = mid + 1;
        System.arraycopy(a, lo, aux, lo, hi + 1 - lo);

        for (int k = lo; k <= hi; k++) {
            if (j > hi) {
                a[k] = (T) aux[i++];
            } else if (i > mid) {
                a[k] = (T) aux[j++];
            } else if (less(aux[j], aux[i])) {
                a[k] = (T) aux[j++];
            } else {
                a[k] = (T) aux[i++];
            }
        }
    }

}