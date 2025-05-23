import org.junit.Test;
import static org.junit.Assert.*;

public class FloydTest {
    private Graph graph = new Graph(true, true);

    @Test
    public void testComputeShortestPaths() {
        graph.addEdge(0, 1, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(2, 0, 4);

        Floyd floyd = new Floyd();
        int[][] distances = floyd.computeShortestPaths(graph);

        assertEquals(0, distances[0][0]);
        assertEquals(3, distances[0][1]);
        assertEquals(4, distances[0][2]);
    }
}