// https://github.com/TheAlgorithms/Java/tree/24f4090210fb68b287df9c4ba37b804c6135ef04/src/main/java/com/thealgorithms/datastructures/graphs/MatrixGraphs.java#L329-L344
public class TempClass {
    public String toString() {
        StringBuilder s = new StringBuilder("    ");
        for (int i = 0; i < this.numberOfVertices(); i++) {
            s.append(i).append(" ");
        }
        s.append(" \n");

        for (int i = 0; i < this.numberOfVertices(); i++) {
            s.append(i).append(" : ");
            for (int j = 0; j < this.numberOfVertices(); j++) {
                s.append(this.adjMatrix[i][j]).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

}