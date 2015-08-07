
package huffman;

import java.util.ArrayList;

/**
 *
 * @author Ryan Darnell
 */
public class PriorityQueue<E> {
    
    ArrayList<Node> array;
    
    public PriorityQueue() {
        array = new ArrayList();
    }
    
    public Node peek() {
        return array.get(0);
    }
    
    public void enqueue(Node node){
        //add code to sort
        int i = 0;
        if (array.size() == 0)
            array.add(node);
        else{
            while (i <= array.size()){
                if (i == array.size()){
                    array.add(node);
                    break;
                }   
                if (array.get(i).getFrequency() > node.getFrequency()) {
                    array.add(i, node);
                    break;
                }
                i++;
            }
        }
    }
    
    public Node dequeue() {
        Node first = array.get(0);
        array.remove(0);
        return first;
    }
    
    public int size(){
        return array.size();
    }
    
}
