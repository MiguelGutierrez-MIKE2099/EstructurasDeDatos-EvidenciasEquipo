/**
 * @author Miguel Gutiérrez
 */

package Evidencia03.ui;

/**
 * A una instancia de la interfaz funcional MenuActionPrototype se le puede asignar la definición de una función lambda.
 */
    @FunctionalInterface
    public interface MenuAccion {
    /**
     * definedAction es el prototipo del método que ayudará a inicializar una instancia de
     * la clase MenuActionPrototype como una función lambda definida.
     */
        void definedAction();
    }

