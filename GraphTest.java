import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class GraphTest {
    private Graph graph;

    @Before
    public void setUp() {
        graph = new Graph(true, true); // Grafo dirigido y ponderado
    }

    @Test
    public void testAddVertex() {
        graph.addVertex(0);
        assertTrue(graph.getAdjList().containsKey(0));
        assertEquals(0, graph.getAdjList().get(0).size());
    }

    @Test
    public void testAddEdge() {
        graph.addEdge(0, 1, 5);
        List<Edge> edges = graph.getAdjList().get(0);
        assertNotNull(edges);
        assertEquals(1, edges.size());
        assertEquals(1, edges.get(0).to);
        assertEquals(5, edges.get(0).weight);
    }

    @Test
    public void testRemoveEdge() {
        graph.addEdge(0, 1, 5);
        graph.removeEdge(0, 1);
        List<Edge> edges = graph.getAdjList().get(0);
        assertEquals(0, edges.size());
    }
}