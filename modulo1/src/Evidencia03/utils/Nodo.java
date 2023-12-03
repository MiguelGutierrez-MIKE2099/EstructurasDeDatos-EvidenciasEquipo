/**
 * @author Miguel Gutiérrez
 */


package Evidencia03.utils;

import java.util.Optional;

public class Nodo <E>{
    E data;
    Optional<Nodo<E>> next;
    public Nodo(E data) {
        this.data = data;
        next = Optional.empty();
    }
}
