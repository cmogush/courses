//WordFrequencies
//2017 Christopher Mogush

import edu.duke.*;
import java.util.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() { //constructor to initialize private variables
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        
        FileResource resource = new FileResource();
        
        for(String s : resource.words()){
            s = s.toLowerCase();
            //if it's not part of the array
            int index = myWords.indexOf(s); 
            if (index == -1){
                myWords.add(s); //add to array
                myFreqs.add(1);
            }
            else {
                int value = myFreqs.get(index); //find the value
                myFreqs.set(index, value+1); //store the value
            }
        }
    }
    
    private int findIndexOfMax(){
        int idxMax = 0;
        int max = 0;
        //returns an int that is the index location of the largest value in myFreqs. 
        for(int k = 0; k < myFreqs.size(); k++){
            if(myFreqs.get(k) > max){
                idxMax = k;
                max = myFreqs.get(k);
            }
        }
        //If there is a tie, then return the first such value
        return idxMax;
    }
    
    public void tester(){
        findUnique();
        for(int k = 0; k < myWords.size(); k++){
            System.out.println(myFreqs.get(k) + "\t" + myWords.get(k));
        }
        //determine and print the word that occurs the most often in a selected file and how many times it occurs
        int index = findIndexOfMax();
        System.out.println("# unique words: " + myWords.size());
        System.out.println("The most occuring word is: " + myWords.get(index) + "\t" + "count: " + myFreqs.get(index));
    }
}
