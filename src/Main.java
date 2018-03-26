import java.util.List;

public class Main {

    public static void main(String[] args) {
        BTree btree = new BTree();

        btree.add(2);
        btree.add(7);
        btree.add(6);
        btree.add(5);
        btree.add(11);
        btree.add(9);
        btree.add(4);
        btree.add(3);
        btree.add(1);

        System.out.println();

        btree.showWide();
    }
}
