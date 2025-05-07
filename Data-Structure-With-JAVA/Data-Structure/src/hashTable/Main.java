package hashTable;
import list.Node;

public class Main {
    public static void main(String[] args) {
        ChainedHashTable hashTable = new ChainedHashTable(11);
        hashTable.insert(10);
        hashTable.delete(20);
        hashTable.insert(20);
        hashTable.insert(5);
        hashTable.insert(80);
        hashTable.insert(90);
        hashTable.insert(20);
        hashTable.insert(44);

        Node<Integer> nd = hashTable.search(44);
        if (nd == null) System.out.println("Search Failed");
        else System.out.println("Found " + nd.item);

        ArrayHashTable arrayHashTable = new ArrayHashTable(11);
        arrayHashTable.insert(10);
        arrayHashTable.delete(20);
        arrayHashTable.insert(20);
        arrayHashTable.insert(5);
        arrayHashTable.insert(80);
        arrayHashTable.insert(90);

        arrayHashTable.delete(20);
        arrayHashTable.delete(44);

        int slot = arrayHashTable.search(80);
        if (slot == ArrayHashTable.NOT_FOUND) System.out.println("Search Failed");
        else System.out.println("Found " + arrayHashTable.getItem(slot));
    }
}
