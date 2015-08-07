
package huffman;

/**
 *
 * @author Ryan Darnell
 */
public class HashTable {
    Node[] array = new Node[128];
    
    public HashTable(){
    
    }
    
    public void insert(Node node){
        array[(int)node.getLetter()] = node;
        node.increase();
    }
    
    public Node getNode(char character){
        return array[(int)character];
    }
    
    public Node getByIndex(int index){
        return array[index];
    }
}
