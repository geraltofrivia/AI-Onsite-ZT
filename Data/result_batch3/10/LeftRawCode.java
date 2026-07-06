// https://github.com/TheAlgorithms/Java/tree/24f4090210fb68b287df9c4ba37b804c6135ef04/src/main/java/com/thealgorithms/geometry/GrahamScan.java#L22-L60
public class TempClass {
    public GrahamScan(Point[] points) {
        // Pre-process points: sort by y-coordinate, then by polar order with respect to the first point
        Arrays.sort(points);
        Arrays.sort(points, 1, points.length, points[0].polarOrder());

        hull.push(points[0]);

        // Find the first point not equal to points[0] (firstNonEqualIndex)
        // and the first point not collinear firstNonCollinearIndex with the previous points
        int firstNonEqualIndex;
        for (firstNonEqualIndex = 1; firstNonEqualIndex < points.length; firstNonEqualIndex++) {
            if (!points[0].equals(points[firstNonEqualIndex])) {
                break;
            }
        }

        if (firstNonEqualIndex == points.length) {
            return;
        }

        int firstNonCollinearIndex;
        for (firstNonCollinearIndex = firstNonEqualIndex + 1; firstNonCollinearIndex < points.length; firstNonCollinearIndex++) {
            if (Point.orientation(points[0], points[firstNonEqualIndex], points[firstNonCollinearIndex]) != 0) {
                break;
            }
        }

        hull.push(points[firstNonCollinearIndex - 1]);

        // Process the remaining points and update the hull
        for (int i = firstNonCollinearIndex; i < points.length; i++) {
            Point top = hull.pop();
            while (Point.orientation(hull.peek(), top, points[i]) <= 0) {
                top = hull.pop();
            }
            hull.push(top);
            hull.push(points[i]);
        }
    }

}