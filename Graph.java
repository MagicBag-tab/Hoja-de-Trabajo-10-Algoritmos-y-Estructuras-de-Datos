
/*
 * Implementación del algoritmo del grafo
 * 
 * @date 22/05/2025
 * 
 * @reference Código sacado del material de Clase de Algoritmos y Estructura de datos
 * 
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Graph {

    private boolean directed;
    private boolean weighted;
    private Map<Integer, List<Edge>> adjList;

    /*
     * Constructor del grafo.
     */

    Graph(boolean directed, boolean weighted) {
        this.directed = directed;
        this.weighted = weighted;
        this.adjList = new HashMap<>();
    }

    /*
     * Añade un nuevo vertice al grafo
     */

    void addVertex(int vertex) {
        adjList.putIfAbsent(vertex, new ArrayList<>());
    }

    /*
     * Añade una nueva arista al grafo, el cual ve de donde a donde debe de ir
     */

    void addEdge(int from, int to, int weight) {
        if (!weighted) {
            weight = 1;
        }

        addVertex(from);
        addVertex(to);

        adjList.get(from).add(new Edge(to, weight));
        if (!directed) {
            adjList.get(to).add(new Edge(from, weight));
        }
    }

    /*
     * Elimina una arista y revisa que en otros vertices ya no exista la relación.
     */

    void removeEdge(int from, int to) {
        List<Edge> edgesFrom = adjList.get(from);
        if (edgesFrom != null) {
            edgesFrom.removeIf(edge -> edge.to == to);
        }
        if (!directed) {
            List<Edge> edgesTo = adjList.get(to);
            if (edgesTo != null) {
                edgesTo.removeIf(edge -> edge.to == from);
            }
        }
    }

    /*
     * Elimina el vertice
     */

    void removeVertex(int vertex) {
        adjList.remove(vertex);

        for (Map.Entry<Integer, List<Edge>> entry : adjList.entrySet()) {
            List<Edge> edges = entry.getValue();
            for (int i = edges.size() - 1; i >= 0; i--) {
                if (edges.get(i).to == vertex) {
                    edges.remove(i);
                }
            }
        }
    }

    /*
     * Muestra el grafo completo junto a donde se dirige
     */

    void printGraph() {
        for (int vertex : adjList.keySet()) {
            System.out.print("Vertex " + vertex + ":");
            for (Edge edge : adjList.get(vertex)) {
                System.out.print(" -> " + edge.to + "(" + edge.weight + ")");
            }
            System.out.println();
        }
    }
}