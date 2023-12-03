/**
 * @author Miguel Gutiérrez
 */


package Evidencia03.utils;

public class Pares <A, B>{
    private A first;
    private B second;

    public Pares(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public void setFirst(A first) {
        this.first = first;
    }

    public void setSecond(B second) {
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }
}
