package BST;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(10);
        bst.insert(20);
        bst.insert(5);
        bst.insert(80);
        bst.insert(90);
        bst.insert(75);
        bst.insert(30);
        bst.insert(77);
        bst.insert(15);
        bst.insert(40);


        Integer del1 = 20;
        bst.delete(del1);
        Integer del2 = 20;
        bst.delete(del2);

        AVLTree avl = new AVLTree();
        avl.insert(10);
        avl.insert(20);
        avl.insert(5);
        avl.insert(80);
        avl.insert(90);
        avl.delete(80);
    }
}
