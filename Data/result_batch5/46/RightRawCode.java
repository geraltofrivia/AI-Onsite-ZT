// https://github.com/kevin-wayne/algs4/tree/72e4012248af7d265ce1f7a1723c691f6f0db50b/src/main/java/edu/princeton/cs/algs4/TwoPersonZeroSumGame.java#L337-L351
public class TempClass {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();

        int m = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);
        double[][] payoff = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                payoff[i][j] = StdRandom.uniformDouble(-0.5, 0.5);
        test("random " + m + "-by-" + n, payoff);
    }

}