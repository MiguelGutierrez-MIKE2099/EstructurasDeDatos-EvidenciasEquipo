/**
 * @author Miguel Gutiérrez
 */

package Evidencia03.utils;

import java.util.*;

/**
 * Esta clase representa un nodo dentro de un grafo.
 */
public class NodoGrafo  {
    /**
     * Datos del nodo:
     */
    private String animal;
    private String nextAnimalFeature;
    private NodoGrafo neighbourYes;
    private NodoGrafo neighbourNo;

    /**
     * Construye un nuevo nodo con los datos especificados.
     *
     * @param animal Los datos a almacenar en el nodo.
     */
    public NodoGrafo(String animal) {
        setAnimal(animal);
        setNextAnimalFeature("Característica del siguinete animal no definida.");
    }

    //Class' setters.

    public void setAnimal(String animal){
        this.animal = animal;
    };

    public void setNextAnimalFeature(String nextAnimalFeature){
        this.nextAnimalFeature = nextAnimalFeature;
    };

    //Class' getters.

    public String getAnimal(){
        return animal;
    }

    /**
     * Obtiene los datos almacenados en el nodo.
     *
     * @return Los datos almacenados en el nodo.
     */
    public String getNextAnimalFeature() {
        return nextAnimalFeature;
    }

    /**
     * Añade un nodo adyacente con un peso de borde asociado.
     */

    public void addNeighbourYes(NodoGrafo neighbourYes){
        this.neighbourYes = neighbourYes;
    }

    public void addNeighbourNo(NodoGrafo neighbourNo){
        this.neighbourNo = neighbourNo;
    }

    /**
     * Obtener Sí y No vecino
     */

    public NodoGrafo getNeighbourYes(){
        return neighbourYes;
    }

    public NodoGrafo getNeighbourNo(){
        return neighbourNo;
    }

    /**
     * Obtiene un mapa de los nodos vecinos y sus pesos de borde asociados.
     *
     * @return Un mapa de los nodos adyacentes y sus pesos de borde.
     */
    public ArrayList<NodoGrafo> getNeighbours() {
        ArrayList<NodoGrafo> neighbours = new ArrayList<>();
        NodoGrafo neighbour;

        neighbour= this.neighbourYes;
        if(neighbour != null){
            neighbours.add(neighbour);
        }

        neighbour= this.neighbourNo;
        if(neighbour != null){
            neighbours.add(neighbour);
        }

        return neighbours;
    }

    @Override
    public String toString() {
        String strOut = "Nodo: " + this.getAnimal();

        for (NodoGrafo entry : this.getNeighbours()) {
            if(entry == null){
                strOut += "\n\tVecino: ---";
            }
            else{
                strOut += "\n\tVecino: " + entry.getAnimal();
            }
        }

        return strOut;
    }

    /**
     * Anula el método equals para comparar nodos según sus valores de datos.
     *
     * @param o El nodo con el que comparar.
     * @return True si los nodos tienen los mismos valores de datos, de lo contrario false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodoGrafo node = (NodoGrafo) o;
        return Objects.equals(animal, node.animal);
    }

    /**
     * Anula el método hashCode para generar un código hash basado en el valor de datos del nodo.
     *
     * @return El código hash calculado en función del valor de los datos.
     */
    @Override
    public int hashCode() {
        return Objects.hash(animal);
    }
}
