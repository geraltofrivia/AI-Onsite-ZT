// https://github.com/kevin-wayne/algs4/tree/72e4012248af7d265ce1f7a1723c691f6f0db50b/src/main/java/edu/princeton/cs/algs4/ClosestPair.java#L50-L85
public class TempClass {
    public ClosestPair(Point2D[] points) {
        if (points == null) throw new IllegalArgumentException("constructor argument is null");
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new IllegalArgumentException("array element " + i + " is null");
        }

        int n = points.length;
        if (n <= 1) return;

        // sort by x-coordinate (breaking ties by y-coordinate via stability)
        Point2D[] pointsByX = new Point2D[n];
        for (int i = 0; i < n; i++)
            pointsByX[i] = points[i];
        Arrays.sort(pointsByX, Point2D.Y_ORDER);
        Arrays.sort(pointsByX, Point2D.X_ORDER);

        // check for coincident points
        for (int i = 0; i < n-1; i++) {
            if (pointsByX[i].equals(pointsByX[i+1])) {
                bestDistance = 0.0;
                best1 = pointsByX[i];
                best2 = pointsByX[i+1];
                return;
            }
        }

        // sort by y-coordinate (but not yet sorted)
        Point2D[] pointsByY = new Point2D[n];
        for (int i = 0; i < n; i++)
            pointsByY[i] = pointsByX[i];

        // auxiliary array
        Point2D[] aux = new Point2D[n];

        closest(pointsByX, pointsByY, aux, 0, n-1);
    }

}