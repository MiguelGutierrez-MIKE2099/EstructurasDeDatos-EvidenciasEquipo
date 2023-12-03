/**
 * @author Miguel Gutiérrez
 */

package Evidencia03.utils;

import java.util.Set;
import java.util.HashSet;

/**
 * Esta clase representa un gráfico dirigido ponderado.
 */
public class Grafo {
    private Set<NodoGrafo> nodes;
    private NodoGrafo root;

    /**
     * Inicializando un gráfico vacío...
     */
    public Grafo() {
        root = new NodoGrafo("Animal nulo");
        root.setNextAnimalFeature("Cuernos");

        nodes = new HashSet<>();
        addEdgeYes(root, new NodoGrafo("Vaca"));
        addEdgeNo(root, new NodoGrafo("Perro"));
    }

    /**
     * Añade un nodo al gráfico si no existe.
     *
     * @param newNode El nodo a agregar.
     */
    public void addNode(NodoGrafo newNode) {
        if (newNode != null && !nodes.contains(newNode)) {
            nodes.add(newNode);
        }
    }

    /**
     * Añade un borde entre dos nodos con un peso asociado.
     */

    //Add yes neighbour.
    public void addEdgeYes(NodoGrafo from, NodoGrafo to) {
        if (from != null && to != null) {
            from.addNeighbourYes(to);
            addNode(from);
            addNode(to);
        }
    }

    //Add no neighbour.
    public void addEdgeNo(NodoGrafo from, NodoGrafo to) {
        if (from != null && to != null) {
            from.addNeighbourNo(to);
            addNode(from);
            addNode(to);
        }
    }

    /**
     * Obtiene el conjunto de nodos en el gráfico.
     *
     * @return El conjunto de nodos.
     */
    public NodoGrafo getRoot() {
        return root;
    }

    /**
     * Obtiene el conjunto de nodos en el gráfico.
     *
     * @return El conjunto de nodos.
     */
    public Set<NodoGrafo> getNodes() {
        return nodes;
    }

}