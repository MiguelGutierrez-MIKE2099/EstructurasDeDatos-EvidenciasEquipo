package evidencia1.utils;

import java.util.Optional;

public class PriorityQueue {
    private ListaEnlazada<Integer> priorityQueue;

    public PriorityQueue() {
        priorityQueue = new ListaEnlazada<>();
        convertToMaxHeap();
    }
    private void convertToMaxHeap() {
        int size = priorityQueue.getSize();

        // Comienza desde el último nodo que tiene hijos
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(size, i);
        }
    }

    private void heapify(int size, int rootIndex) {
        int largest = rootIndex;
        int leftChildIndex = 2 * rootIndex + 1;
        int rightChildIndex = 2 * rootIndex + 2;

        // Compara con el hijo izquierdo
        if (leftChildIndex < size && priorityQueue.get(leftChildIndex).get() > priorityQueue.get(largest).get()) {
            largest = leftChildIndex;
        }

        // Compara con el hijo derecho
        if (rightChildIndex < size && priorityQueue.get(rightChildIndex).get() > priorityQueue.get(largest).get()) {
            largest = rightChildIndex;
        }

        if (largest != rootIndex) {
            swap(rootIndex, largest);
            heapify(size, largest);
        }
    }

    private void swap(int a, int b) {
        int tempA = priorityQueue.get(a).get();
        int tempB = priorityQueue.get(b).get();

        priorityQueue.update(tempA, tempB);
        priorityQueue.update(tempB, tempA);
    }

    public void push(int element) {
        priorityQueue.add(element);
        convertToMaxHeap();
    }

    public Optional<Integer> pop() {
        if (isEmpty()) {
            return Optional.empty();
        }

        int maxElement = priorityQueue.get(0).get();
        int lastElement = priorityQueue.get(priorityQueue.getSize() - 1).get();

        // Reemplaza el elemento de mayor prioridad con el último elemento
        priorityQueue.update(maxElement, lastElement);
        priorityQueue.remove(lastElement);
        heapify(priorityQueue.getSize(), 0);

        return Optional.of(maxElement);
    }

    public Optional<Integer> peek() {
        if (isEmpty()) {
            return Optional.empty();
        }

        return priorityQueue.get(0);
    }

    public int size() {
        return priorityQueue.getSize();
    }

    public boolean isEmpty() {
        return priorityQueue.getSize() == 0;
    }
}