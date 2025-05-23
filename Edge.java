/*
 * Implementación de la clase de arista para ser utilizada en la clase del grafo.
 * 
 * @date 22/05/2025
 * 
 * @reference Código sacado del material de Clase de Algoritmos y Estructura de datos
 * 
 */
class Edge {
    int to;
    int weight;

    /*
     * Constructor de la arista que muestra su peso y a donde va.
     */

    Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}