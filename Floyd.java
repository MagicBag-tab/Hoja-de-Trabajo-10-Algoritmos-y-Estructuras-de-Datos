/*
 * Implementación del Algoritmo de Floyd
 * 
 * @reference    Link de referencia algoritmo de la ruta más corta:
 *              https://www.programiz.com/dsa/floyd-warshall-algorithm
 * 
 *              Link de referencia del algoritmo para calcular el centro:
 *              https://codeforces.com/blog/entry/17974 
 *              Utiliza también la teoría dada en la asiganación
 */

import java.util.*;

public class Floyd {

    // el numero más grande para simular los infinitos
    private static final int INF = Integer.MAX_VALUE / 2;

    /*
     * Encuentra el camino más corto para llegar de un vértice a cualquier otro
     * vértice
     * utilizando el algoritmo de Floyd
     */
    public int[][] computeShortestPaths(Graph graph) {
        // Obtener el conjunto de todos los vértices del grafo
        Set<Integer> vertices = graph.getAdjList().keySet();
        int n = vertices.size();
        // Si el grafo está vacío, devolver una matriz vacía
        if (n == 0)
            return new int[0][0];

        // Convertir el conjunto de vértices a un array ordenado para asignar índices
        int[] vertexArray = vertices.stream().mapToInt(Integer::intValue).sorted().toArray();
        // Mapear cada vértice a un índice en la matriz (0 a n-1)
        Map<Integer, Integer> vertexToIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            vertexToIndex.put(vertexArray[i], i);
        }

        // Inicializar la matriz de distancias con infinito, excepto las diagonales
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        // Llenar la matriz con las distancias iniciales basadas en las aristas del
        // grafo
        for (int vertex : vertices) {
            int u = vertexToIndex.get(vertex); // Índice del vértice origen
            for (Edge edge : graph.getAdjList().getOrDefault(vertex, new ArrayList<>())) {
                int v = vertexToIndex.get(edge.getTo()); // Índice del vértice destino
                dist[u][v] = edge.getWeight(); // Asignar el peso de la arista
                // Si el grafo no es dirigido, agregar la arista en ambas direcciones
                if (!graph.isDirected()) {
                    dist[v][u] = edge.getWeight();
                }
            }
        }

        /*
         * Actualizar distancias usando todos los vértices como intermediarios
         */
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // Si hay un camino más corto a través del vértice k, actualizar la distancia
                    if (dist[i][k] != INF && dist[k][j] != INF && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        return dist;
    }

    /*
     * Calcular el centro del grafo, definido como el vértice (o vértices) con la
     * menor excentricidad
     */
    public List<Integer> findCenter(Graph graph) {
        /*
         * Utiliza el algoritmo de Floyd y encuentra la distancia más corta
         * para luego calcular la excentricidad de cada vértice.
         */

        // Obtener la matriz de distancias más cortas
        int[][] dist = computeShortestPaths(graph);
        // Obtener el conjunto de vértices del grafo
        Set<Integer> vertices = graph.getAdjList().keySet();
        int n = vertices.size();
        // Si el grafo está vacío, devolver una lista vacía
        if (n == 0)
            return new ArrayList<>();

        // Convertir el conjunto de vértices a un array ordenado
        int[] vertexArray = vertices.stream().mapToInt(Integer::intValue).sorted().toArray();
        // Mapear cada vértice a un índice en la matriz
        Map<Integer, Integer> vertexToIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            vertexToIndex.put(vertexArray[i], i);
        }

        // Lista para almacenar los vértices que forman el centro
        List<Integer> centers = new ArrayList<>();
        // Inicializar la menor excentricidad con el valor infinito
        int minEccentricity = INF;

        // Calcular la excentricidad para cada vértice
        for (int i = 0; i < n; i++) {
            int maxDist = 0; // Distancia máxima desde el vértice i
            boolean reachable = false; // Indica si el vértice tiene al menos un camino
            for (int j = 0; j < n; j++) {
                // Si hay un camino, actualizar la distancia máxima
                if (dist[i][j] != INF) {
                    maxDist = Math.max(maxDist, dist[i][j]);
                    reachable = true;
                }
            }
            // Si el vértice es alcanzable y tiene una excentricidad menor, actualizar el
            // centro
            if (reachable && (maxDist < minEccentricity || minEccentricity == INF)) {
                minEccentricity = maxDist;
                centers.clear();
                centers.add(vertexArray[i]);
            }
            // Si la excentricidad es igual a la mínima, agregar el vértice al centro
            else if (reachable && maxDist == minEccentricity) {
                centers.add(vertexArray[i]);
            }
        }

        return centers; // Devolver la lista de vértices que forman el centro del grafo
    }
}