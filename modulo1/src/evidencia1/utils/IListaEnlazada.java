package evidencia1.utils;
import java.util.Optional;

/**
 * Interfaz base para la implementación de listas enlazadas
 * */
public class IListaEnlazada {
    void add(E e);
    void remove(E e);
    Optional<E> get(int index);
    void update(E oldValue, E newValue);
    int getSize();
    boolean contains(E t);
}
