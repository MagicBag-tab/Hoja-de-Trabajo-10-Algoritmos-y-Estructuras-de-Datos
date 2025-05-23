/*
 * Implementación del main que contiene el grafo
 */

import java.util.List;

public class Main {
    public static void main(String[] args) {

        String[] stations = {
                "A: Ciudad de Guatemala",
                "B: Zacapa",
                "C: Chiquimula",
                "D: Quetzaltenango",
                "E: Cobán"
        };

        Graph graph = new Graph(true, true);

        graph.addEdge(0, 1, 3);
        graph.addEdge(0, 3, 7);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 4, 8);
        graph.addEdge(2, 3, 2);
        graph.addEdge(3, 4, 3);
        graph.addEdge(4, 0, 4);

        System.out.println("Conexiones de la red de transporte interdepartamental:");
        graph.printGraph();

        Floyd floyd = new Floyd();
        List<Integer> centers = floyd.findCenter(graph);

        System.out.println("\nCentro del grafo:");
        for (int center : centers) {
            System.out.println(stations[center]);
        }
    }
}