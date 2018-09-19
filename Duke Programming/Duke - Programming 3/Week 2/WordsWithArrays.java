//WordsWithArrays
//2017 Christopher Mogush

import java.util.*;
import edu.duke.*;

public class WordsWithArrays {
        
    StorageResource myWords;
    
    public WordsWithArrays(){ //construtor
        myWords = new StorageResource();
    }
    
    public void readWords(){ //reads all words from file into storage resource MyWords
        myWords.clear();
        FileResource resource = new FileResource();
        for(String word : resource.words()){
            myWords.add(word.toLowerCase());
        }
    }
    
    public boolean contains(String[] list, String word, int number){ //is word in array?
        for(int k = 0; k < number; k++){
            if(list[k].equals(word)){
                return true; //if yes
            }
        }
        return false; //if no
    }
    
    public int numberOfUniqueWords(){
        int numStored = 0;
        String[] words = new String[myWords.size()]; //create an array with max size of StorageResource (in case all words are unique)
        for(String s : myWords.data()){ //provides iterable access to myWords, one string at a time
            if(! contains(words, s, numStored)){ //if the current word "s" does not yet exist in the String array "words"
                words[numStored] = s; //then add the word to the string array at location numStored
                numStored++; //go to next storage location in array and add 1 to the total numStored
            }
        }
        return numStored;
    }
    
    public void tester(){
        readWords();
        System.out.println("number of words read: " + myWords.size());
        int unique = numberOfUniqueWords();
        System.out.println("unique words " + unique);
    }
}
