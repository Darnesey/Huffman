
package huffman;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Ryan Darnell
 */
public class Storage {
    int[] array;
    HashTable table = new HashTable();
    
    public Storage() {
        //blank constructor
    }
    
    public HashTable load() {
        ArrayList<Character> array = new ArrayList<>();
        try {
            FileReader file = new FileReader("text.txt");
            Scanner inFile = new Scanner(file);
            inFile.useDelimiter("");
            String in = "";
            char inc;
            //in case load file is empty
            if (!inFile.hasNext())
                return null;
            while (inFile.hasNext()) {
                //grab single characters
                in = inFile.next();
                inc =in.charAt(0);
                array.add(inc);
            }
            
            inFile.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        hashing(array);
        return table;
    }

    public void save(Node root) {
        
        try {
            try (PrintWriter outFile = new PrintWriter("Compressed.txt")) {
                FileReader file = new FileReader("text.txt");
                Scanner inFile = new Scanner(file);
                //issue with setting Delimiter as '\n' so delimiter set for characters instead
                inFile.useDelimiter("");
                String line = "";
                String key = "";
                
                //building text file
                while (inFile.hasNext()) {
                    line = inFile.next();
                    char character = line.charAt(0);
                    key = table.getNode(character).getKey();
                    outFile.print(key);
                }
                //close
                outFile.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find that file");
        }
    }
    
    public void hashing(ArrayList<Character> array){
        int i = 0;
        while (i < array.size()) {
            if (table.getNode(array.get(i)) == null)
                table.insert(new Node(array.get(i)));
            else
                table.getNode(array.get(i)).increase();
            
            i++;
        }
    }
    
    public void readAndWrite(Node root){
        try{
            FileReader file = new FileReader("Compressed.txt");
            PrintWriter outFile = new PrintWriter("Decompressed.txt");
            Scanner inFile = new Scanner(file);
            inFile.useDelimiter("\n");
            String in;
            Node current = root;
            
            while(inFile.hasNextLine()){
                in = inFile.nextLine();
                String key = "";
                for (int i = 0; i < in.length(); i++) {
                    key += in.charAt(i);
                    for (int j = 0; j < key.length(); j++) {
                        if (key.charAt(j) == '1')
                            current = current.getRight();
                        else
                            current = current.getLeft();
                    }
                    if (current.getLetter() == '\b'){
                        //reset root
                        current = root;
                    } else { //found a valid character
                        System.out.print("" + current.getLetter());
                        outFile.print(current.getLetter());
                        key = ""; //reset key
                        current = root;
                    }
                }
                
            }
            outFile.close();
            inFile.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}

