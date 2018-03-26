import java.util.ArrayList;
import java.util.List;

/**
 * Lista genérica
 * <p>
 * La finalidad de esta lista es implementar métodos necesarios
 * para le ejecución del programa, debería contener los métodos
 * básicos de una lista doblemente enlazada.
 *
 * @param <T>
 */
public class MyList<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;


    // Constructor
    public MyList(Node<T> first, Node<T> last, int size) {
        this.first = first;
        this.last = last;
        this.size = size;
    }


    // Constructor
    public MyList() {
        this.first = this.last = null;
        this.size = 0;
    }


    /**
     * Agrega un elemento a la lista.
     *
     * @param data Tipo de dato genérico para almacenar en la lista.
     * @return Boolean Regresa verdadero si se ingresó el dato,
     * de lo contrario regresa false.
     */
    public boolean add(T data) {
        boolean retval = false;
        Node<T> el = new Node<>(data);

        if (isEmpty()) {
            first = last = el;
            size++;
            retval = true;
        } else {
            last.next = el;
            el.back = last;
            last = el;
            size++;
            retval = true;
        }

        return retval;
    }


    /**
     * Obtiene todos los elementos que están almacenados en la lista.
     *
     * @return List<T> Regresa una lista de tipos de datos genéricos
     * con la finalidad de que el usuario programador
     * pueda hacer manipulación a través de los métodos
     * del objeto genérico.
     */
    public List<T> getAll() {
        List<T> retval = new ArrayList<>();

        if (!isEmpty()) {
            Node<T> aux = first;
            while (aux != null) {
                retval.add(aux.getData());
                aux = aux.getNext();
            }
        }

        return retval;
    }


    /**
     * Obtiene el tamaño de la lista.
     *
     * @return int Tamaño
     */
    public int getSize() {
        return this.size;
    }


    /**
     * Verifica si la lista está vacía.
     *
     * @return Boolean Retorna un valor verdadero si la lista está vacía,
     * y false, si no lo está.
     */
    public boolean isEmpty() {
        return size == 0;
    }


    //===================================================
    // Private classes
    //===================================================


    /**
     * Clase privada usada por la clase MyList
     * <p>
     * Esta clase nodo es usada para enlazar los diferentes
     * objetos hacia un item.
     *
     * @param <T>
     */
    private class Node<T> {
        private T data;
        private Node<T> next;
        private Node<T> back;

        public Node(T data, Node<T> next, Node<T> back) {
            this.data = data;
            this.next = next;
            this.back = back;
        }

        public Node() {
            this.data = null;
            this.next = null;
            this.back = null;
        }

        public Node(T data) {
            this.data = data;
            this.next = this.back = null;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public Node<T> getBack() {
            return back;
        }

        public void setBack(Node<T> back) {
            this.back = back;
        }
    }
}
