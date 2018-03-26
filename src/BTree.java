import java.util.List;

/**
 * Clase Binary Tree
 */
public class BTree {
    private int size;
    private Node root;
    private int maxLevel;
    private MyList<Pair> wideList;

    // Constructor
    public BTree() {
        this.root = null;
        this.size = 0;
        this.maxLevel = 0;
        wideList = new MyList<>();
    }


    //Constructor
    public BTree(Node root) {
        this.root = root;
        this.size = 0;
        this.maxLevel = 0;
        wideList = new MyList<>();
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
            root.setLevel(maxLevel++);
            System.out.println("Se ha establecido el valor " + data + " para el nodo raíz.\n");
        } else {
            add(data, root);
        }
    }


    /**
     * Muestra los elementos del árbol de acuerdo a los niveles
     * del árbol, es decir, partiendo de la fuente root como
     * nivel 0, hasta el último nodo y su respectivo nivel de
     * profundidad.
     */
    public void showWide() {

        if (root == null) {
            System.out.println("El árbol está vacío.");
        } else {
            // Verifica que haya datos en la lista wideList
            fillWideList(root);

            System.out.println("\nMostrando los datos en por anchura:");

            List<Pair> li = wideList.getAll();
            int len = getMaxLevel();

            for (int i = 0; i <= len; i++) {
                System.out.printf("Nivel " + i + ": \t");
                for (Pair<Integer, Integer> p : li) {
                    if (i == p.getFirst()) {
                        System.out.printf(p.getSecond() + ", \t");
                    }
                }
                System.out.println();
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
     * Obtiene el nivel máximo de profundidad del árbol binario.
     * @return
     */
    public int getMaxLevel() {
        return maxLevel;
    }

    //===================================================
    //  Private methods
    //===================================================


    /**
     * Establece al nodo raíz del árbol.
     *
     * @param root Nodo que será el nodo raíz del árbol.
     */
    private void setRoot(Node root) {
        this.root = root;
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

                // Agrega su nivel de profundidad
                root.getLeft().setLevel(root.getLevel() + 1);

                // Establece el nivel máximo de profundidad del árbol
                maxLevel = root.getLeft().getLevel() > maxLevel ? root.getLeft().getLevel() : maxLevel;

                System.out.println("Agregando hijo: " + root.getLeft().getData() + " <- a padre: " + root.getData());
            } else {
                // Se vuelve a llamar para encontrar otra posición en el árbol, pasando el nodo izquierdo.
                add(data, root.getLeft());
            }
        } else if (data > root.getData()) {
            if (root.getRight() == null) {
                // Agrega el nuevo nodo derecho
                root.setRight(new Node(data));

                // Agrega su nivel de profundidad
                root.getRight().setLevel(root.getLevel() + 1);

                // Establece el nivel máximo de profundidad del árbol
                maxLevel = root.getRight().getLevel() > maxLevel ? root.getRight().getLevel() : maxLevel;

                System.out.println("Agregando a padre: " + root.getData() + " -> un hijo: " + root.getRight().getData());
            } else {
                // Se vuelve a llamar para encontrar otra posición en el árbol, pasando el nodo derecho.
                add(data, root.getRight());
            }
        }
    }


    /**
     * Esta función es un método auxiliar para obtener los datos
     * del árbol binario de acuerdo a los niveles de profundidad.
     * Este método es un método inneficiente que verifica si el
     * elemento que se va a agregar esté o no en la lista, ya que
     * de acuerdo a la recursividad del método showWide() y fillWideList
     * si no se verifica el dato en la lista, éste se repetirá tantas
     * veces sea por la recursividad del método fileWideList.
     *
     * @param data Dato a buscar en la lista.
     * @return boolean Regresa true si el elemento ya está en la lista,
     * de lo contrario regresa false.
     */
    private boolean contains(Pair data) {
        boolean retval = false;

        for (Pair<Integer, Integer> l : wideList.getAll()) {
            if (l.getFirst() == data.getFirst() && l.getSecond() == data.getSecond()) {
                retval = true;
            }
        }

        return retval;
    }


    /**
     * Este método privado es utilizado para rellenar los datos
     * del árbol binario en un lista donde se pueda observar
     * la profundidad del elemento y su correspondiente valor.
     *
     * @param el Nodo del que partirá para verificar los hijos,
     *           izquierdo y derecho.
     */
    private void fillWideList(Node el) {
        if (el != null) {

            // Si el elemento no está en la lista, lo agrega
            if (!contains(new Pair(el.getLevel(), el.getData()))) {
                wideList.add(new Pair(el.getLevel(), el.getData()));
            }

            // Si el nodo izquierdo existe
            if (el.getLeft() != null) {
                el.getLeft().setFather(el);
                el.getLeft().setLevel(el.getLevel() + 1);

                // Si el elemento no está en la lista, lo agrega
                if (!contains(new Pair(el.getLevel(), el.getData()))) {
                    wideList.add(new Pair(el.getLevel(), el.getData()));
                }

                fillWideList(el.getLeft());
            }

            // Si el nodo derecho existe
            if (el.getRight() != null) {
                el.getRight().setFather(el);
                el.getRight().setLevel(el.getLevel() + 1);

                // Si el elemento no está en la lista, lo agrega
                if (!contains(new Pair(el.getLevel(), el.getData()))) {
                    wideList.add(new Pair(el.getLevel(), el.getData()));
                }

                fillWideList(el.getRight());
            }
        }
    }


    //===================================================
    // Private classes
    //===================================================
    private class Node {
        private int data;
        private Node father;
        private Node left;
        private Node right;
        private boolean visited;
        private boolean discovered;
        private int level;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.visited = false;
            this.discovered = false;
            this.level = -1;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }

        public boolean isDiscovered() {
            return discovered;
        }

        public void setDiscovered(boolean discovered) {
            this.discovered = discovered;
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
