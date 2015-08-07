
package huffman;

/**
 *
 * @author Ryan Darnell
 */
public class Node {
    char character;
    int frequency = 0;
    Node left;
    Node right;
    String key = "";

    public Node(char letter) {
        this.character = letter;
    }
    
    
    //increase frequency
    public void increase(){
        frequency++;
    }

    public char getLetter() {
        return character;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
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
    
    public void buildKey(char num){
        key = num + key;
    }
    
    public String getKey(){
        return key;
    }
    
    
}
