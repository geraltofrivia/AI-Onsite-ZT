// https://github.com/kevin-wayne/algs4/tree/72e4012248af7d265ce1f7a1723c691f6f0db50b/src/main/java/edu/princeton/cs/algs4/Counter.java#L95-L114
public class TempClass {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        // create n counters
        Counter[] hits = new Counter[n];
        for (int i = 0; i < n; i++) {
            hits[i] = new Counter("counter" + i);
        }

        // increment trials counters at random
        for (int t = 0; t < trials; t++) {
            hits[StdRandom.uniformInt(n)].increment();
        }

        // print results
        for (int i = 0; i < n; i++) {
            StdOut.println(hits[i]);
        }
    }

}