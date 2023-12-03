/**
 * @author Miguel Gutiérrez
 */


package Evidencia03.utils;

import java.util.NoSuchElementException;
import java.util.Optional;

public class LinkedList<E> implements ILinkedList<E> {
    private Optional<Nodo<E>> root;
    private int size;

    public LinkedList(){
        root = Optional.empty();
        size = 0;
    }

    public LinkedList(int nNodes){
        root = Optional.empty();
        size = 0;

        for(int i = 0; i < nNodes; ++i){
            this.add(null);
        }
    }

    @Override
    public void add(E e) {
        Nodo<E> newNode = new Nodo<>(e);

        if(root.isEmpty()){
            root = Optional.of(newNode);
        }
        else{
            Optional<Nodo<E>> currentNode = root;

            while(currentNode.get().next.isPresent()){
                currentNode = currentNode.get().next;
            }

            currentNode.get().next = Optional.of(newNode);
        }

        increaseSize();
    }

    public void addFirst(E e){
        Nodo<E> newNode = new Nodo<>(e);

        if(root.isEmpty()){
            root = Optional.of(newNode);
        }
        else{
            newNode.next = root;
            root = Optional.of(newNode);
        }

        increaseSize();
    }

    public void set(int index, E newValue) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        int currentIndex = 0;
        Optional<Nodo<E>> currentNode = root;

        while (currentIndex != index) {
            currentNode = currentNode.get().next;
            currentIndex++;
        }

        currentNode.get().data = newValue;
    }

    @Override
    public void update(E oldValue, E newValue) {
        Optional<Nodo<E>> currentNode = root;

        while (currentNode.isPresent() && !currentNode.get().data.equals(oldValue)) {
            currentNode = currentNode.get().next;
        }

        if (currentNode.isPresent()) {
            currentNode.get().data = newValue;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public boolean contains(E t) {
        Optional<Nodo<E>> currentNode = root;

        while(!currentNode.get().data.equals(t) && currentNode.get().next.isPresent()){
            currentNode = currentNode.get().next;
        }

        if(currentNode.get().data.equals(t)){
            return true;
        }
        return false;

    }

    @Override
    public Optional<E> get(int index) {
        if(index < 0 || index >= size ){
            throw new IndexOutOfBoundsException();
        }

        int currentIndex = 0;
        Optional<Nodo<E>> currentNode = root;

        while(currentIndex != index){
            currentNode = currentNode.get().next;
            ++currentIndex;
        }

        return Optional.of(currentNode.get().data);
    }

    @Override
    public void remove(E e) {
        if (root.isEmpty()) {
            throw new NoSuchElementException();
        }

        if (root.get().data.equals(e)) {
            root = root.get().next;
            decreaseSize();
            return;
        }

        Optional<Nodo<E>> currentNode = root;
        Optional<Nodo<E>> prevNode = Optional.empty();

        while (currentNode.isPresent()) {
            if (currentNode.get().data.equals(e)) {
                prevNode.get().next = currentNode.get().next;
                decreaseSize();
                return;
            }
            prevNode = currentNode;
            currentNode = currentNode.get().next;
        }

        throw new NoSuchElementException();
    }

    @Override
    public int getSize() {
        return size;
    }

    private void increaseSize(){
        ++size;
    }

    private void decreaseSize(){
        --size;
    }

}
