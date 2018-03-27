/**
 * Clase Binary Tree
 */
public class BTree {
    private int size;
    private Node root;
    private Node minor;

    // Constructor
    public BTree() {
        this.root = null;
        this.size = 0;
    }


    //Constructor
    public BTree(Node root) {
        this.root = root;
        this.size = 0;
    }


    /**
     * Agrega un elemento al árbol.
     *
     * @param data int Dato que se almacenará en el árbol binario.
     */
    public void add(int data) {
        Node el = new Node(data);

        if (root == null) {
            this.setRoot(el);
            size++;
            System.out.println("\nSe ha establecido el valor " + data + " para el nodo raíz.\n");
        } else {
            add(data, root);
        }
    }


    /**
     * Método auxiliar para agregar un elemento al árbol.
     * <p>
     * Este método es un método auxiliar para la clase BTree
     * lo que hace es ir recorriendo todos los nodos del árbol
     * de forma recursiva y verificar cuál es la posición correcta
     * que debería tener el nuevo dato a ingresar.
     *
     * @param data Dato a ingresar en el árbol binario.
     * @param root Nodo donde se verificará su hijo izquierdo y derecho.
     */
    private void add(int data, Node root) {

        if (data < root.getData()) {
            if (root.getLeft() == null) {
                // Agrega el nuevo nodo izquierdo
                root.setLeft(new Node(data));

                // Establece el padre del nuevo nodo
                root.getLeft().setFather(root);

                // Agrega su nivel de profundidad
                root.getLeft().setLevel(root.getLevel() + 1);

                size++;

                System.out.println("Agregando hijo: " + root.getLeft().getData() + " <- a padre: " + root.getData());
            } else {
                // Se vuelve a llamar para encontrar otra posición en el árbol, pasando el nodo izquierdo.
                add(data, root.getLeft());
            }
        } else if (data > root.getData()) {
            if (root.getRight() == null) {
                // Agrega el nuevo nodo derecho
                root.setRight(new Node(data));

                // Establece el padre del nuevo nodo
                root.getRight().setFather(root);

                // Agrega su nivel de profundidad
                root.getRight().setLevel(root.getLevel() + 1);

                size++;

                System.out.println("Agregando a padre: " + root.getData() + " -> un hijo: " + root.getRight().getData());
            } else {
                // Se vuelve a llamar para encontrar otra posición en el árbol, pasando el nodo derecho.
                add(data, root.getRight());
            }
        }
    }


    /**
     * Llama a la función privada preOrder.
     */
    public void preOrder() {
        preOrder(root);
    }


    /**
     * Muestra los datos en forma de preOrder
     * esta es una función adicional a la pública
     * por la razón de que se está llamando recursivamente.
     *
     * @param root Nodo actual desde donde se verificarán
     *             los datos de los nodos hijos.
     */
    private void preOrder(Node root) {
        if (root != null) {
            System.out.print(root.getData() + " - ");
            preOrder(root.getLeft());
            preOrder(root.getRight());
        }
    }


    /**
     * Llama a la función privada InOrder.
     */
    public void inOrder() {
        inOrder(root);
    }


    /**
     * Muestra los datos en forma de InOrder
     * es una función privada ya que a diferencia
     * de la función pública del mismo nombre,
     * ésta se está llamando recursivamente.
     *
     * @param root Nodo actual desde donde se verificarán
     *             los datos de los nodos hijos
     */
    private void inOrder(Node root) {
        if (root != null) {
            inOrder(root.getLeft());
            System.out.print(root.getData() + " - ");
            inOrder(root.getRight());
        }
    }


    /**
     * Llama a la función privada postOrder.
     */
    public void postOrder() {
        postOrder(root);
    }


    /**
     * Muestra los datos en la forma PostOrder
     * esta función privada a diferencia de la
     * función pública del mismo nombre,
     * se está llamando de forma recursiva.
     *
     * @param root Nodo actual desde donde se verificarán
     *             los datos de los nodos hijos.
     */
    private void postOrder(Node root) {
        if (root != null) {
            postOrder(root.getLeft());
            postOrder(root.getRight());
            System.out.print(root.getData() + " - ");
        }
    }


    /**
     * Obtiene el elemento menor del árbol.
     * <p>
     * Para la implementación de este método es necesario verificar
     * que el árbol no esté vacío, si no lo está entonces manda el
     * dato del nodo.
     *
     * @return int Si el árbol está vacío devuelve un número negativo
     * el cual es: -999, se debe de tener cuidado al tratar
     * con este error, ya que no es posible devolver un
     * número de error como tal ya que se espera que devuelva
     * un número entero, y el número entero podría estar dentro
     * del árbol, CAUSANDO UN BUG EN EL PROGRAMA.
     */
    public int getMinor() {
        int retval = -999;

        if (isEmpty()) {
            System.out.println("El árbol está vacío.");
        } else {
            if (size == 1) {
                retval = root.getData();
            } else {
                minor = root;
                Node aux = null;

                while (minor != null) {
                    retval = minor.getData();
                    aux = minor;
                    minor = minor.getLeft();
                }
                minor = aux;
            }
        }

        return retval;
    }


    /**
     * Eliminar el elemento menor del árbol binario.
     */
    public void removeMinor() {
        int numMin = getMinor();

        if (numMin != -999) {
            // El puntero menor ya está posicionado, no hace
            // falta realizar todo el recorrido nuevamente,
            // solo verifica que el elemento a eliminar sea
            // el único elemento del árbol
            if (size == 1) {
                System.out.println("Eliminando el primer elemento: " + root.getData());
                setRoot(null);
                size--;
            } else {
                if (minor == root) {
                    if (root.getLeft() != null) {
                        setRoot(root.getLeft());
                        root.setRight(minor.getRight());
                        minor.getRight().setFather(root);

                        minor.setLeft(null);
                        minor.setRight(null);
                    } else {
                        setRoot(root.getRight());
                        root.setFather(null);
                        minor.setRight(null);
                    }
                } else {
                    // Si este nodo tiene más nodos hijos a su derecha
                    if (minor.getRight() != null) {
                        Node childRight = minor.getRight();
                        minor.getFather().setLeft(childRight);
                    } else {
                        minor.getFather().setLeft(null);
                    }
                }
                minor = null;
                size--;
                System.out.println("Elemento eliminado correctamente.");
            }
        }
    }


    /**
     * Verifica si el árbol está vacío
     *
     * @return boolean  Regresa verdadero si el árbol está vacío,
     * si no, regresa falso.
     */
    public boolean isEmpty() {
        return this.size == 0;
    }


    /**
     * Establece al nodo raíz del árbol.
     *
     * @param root Nodo que será el nodo raíz del árbol.
     */
    private void setRoot(Node root) {
        this.root = root;
    }


    //===================================================
    // Private classes
    //===================================================
    private class Node {
        private int data;
        private Node father;
        private Node left;
        private Node right;
        private int level;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.level = -1;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getFather() {
            return father;
        }

        public void setFather(Node father) {
            this.father = father;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}
