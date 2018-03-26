/**
 * Clase para almacenar un conjunto de atributos para un objeto.
 *
 * @param <A> Llave Establece la llave para el objeto
 * @param <B> Valor Establece el valor para el objeto
 */
public class Pair<A, B> {
    private final A first;
    private final B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public Pair<A, B> getObject() {
        return this;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

}
