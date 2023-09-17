/**
 * @author Miguel Gutierrez
 */

package evidencia1;

import evidencia1.ui.CLI;
import evidencia1.utils.ListaEnlazada;
import evidencia1.utils.PriorityQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        int i;

        ListaEnlazada<Integer> linkedList = new ListaEnlazada<>();
        linkedList.add(3);
        linkedList.add(1);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.add(2);
        for (i = 0; i < linkedList.getSize(); ++i) {
            System.out.println(linkedList.get(i).get());
        }
        System.out.println();

        PriorityQueue priorityQueue = new PriorityQueue();
        List<Integer> maxElements = new ArrayList<>();

        while (!priorityQueue.isEmpty()) {
            Optional<Integer> maxElement = priorityQueue.pop();
            if (maxElement.isPresent()) {
                maxElements.add(maxElement.get());
            }
        }

        // Imprimir los elementos máximos en orden descendente
        for (i = maxElements.size() - 1; i >= 0; i--) {
            System.out.println("Elemento máximo: " + maxElements.get(i));
        }

        CLI.launchApp();
    }
}