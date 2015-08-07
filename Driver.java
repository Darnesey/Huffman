
package huffman;

/**
 *
 * @author Ryan Darnell
 */
public class Driver {
    
    /**
     * The following code builds a Huffman Tree out of a single text file to 
     * be compressed. No bit twiddling was used yet to truly compress a file.
     */

   
    public static void main(String[] args) {
        
        int n = (int) '\n';
        System.out.println(n);
        
        Storage sto = new Storage();
        PriorityQueue queue = new PriorityQueue();
        
        //load from file and return hash table
        HashTable table = sto.load();
        
        int i = 0;
        while (table.array.length > i) {
            if (table.getByIndex(i) != null)
                queue.enqueue(table.getByIndex(i));
            i++;
        }
        int[] x = sto.array;
        
        //build the tree now!
        i = 0;
        Node combine;
        while(queue.size() > 1) {
            Node left = queue.dequeue();
                assignKeys(left, '0'); //building the keys for traversal
            Node right = queue.dequeue();
                assignKeys(right, '1');
            int sum = left.getFrequency() + right.getFrequency();
            combine = new Node('\b'); //because marks the... branch!
            combine.setFrequency(sum);
            combine.setLeft(left);
            combine.setRight(right);
            queue.enqueue(combine);
        }
        
        //Tree is built! Create and pass root
        Node root = queue.dequeue();
        
        //Save new "compressed" file
        sto.save(root);
        
        //print to System.out and write to text file
        sto.readAndWrite(root);
        
        
    }
    
    public static void assignKeys(Node current, char build){
        if (current == null)
            return;
        else {
            assignKeys(current.getLeft(), build);
            assignKeys(current.getRight(), build);
            current.buildKey(build);
        }
    }
    
}
