//2018 Christopher Mogush
//WordGramTester

import java.util.*;
import edu.duke.*;
import java.util.Scanner;

public class WordGramTester {
    //builds and prints several WordGrams from a String.
    public void testWordGram(){
        String source = "this is a test this is a test this is a test of words";
        String[] words = source.split("\\s+"); //splits the source into the array
        int size = 4;
        System.out.println("index"+"\t"+"wgLength"+"\t"+"wordgram");
        for(int index = 0; index <= words.length - size; index += 1) {
            WordGram wg = new WordGram(words,index,size);
            System.out.println(index+"\t"+wg.length()+"\t"+wg);
        }
    }
    
    //tests if two WordGrams are equivalent
    public void testWordGramEquals(){
        String source = "this is a test this is a test this is a test of words";
        String[] words = source.split("\\s+");
        ArrayList<WordGram> list = new ArrayList<WordGram>(); //arraylist of wordgrams
        int size = 4;
        //create a new wordgram for each entry, the index changing each time
        for(int index = 0; index <= words.length - size; index += 1) {
            WordGram wg = new WordGram(words,index,size);
            list.add(wg);
        }
        WordGram first = list.get(0);
        System.out.println("checking: "+first);
        for(int k=0; k < list.size(); k++){
              if (first.equals(list.get(k))) {
                System.out.println("matched at "+k+" "+list.get(k));
            }
        }
    }
    
    public void testWordGramShift(){
       String source = "just testing some words out here";
       String[] words = source.split("\\s+");
       int size = 4;
       for(int index = 0; index <= words.length - size; index += 1) {
           WordGram wg = new WordGram(words,index,size);
           System.out.println(index+"\t"+wg.length()+"\t"+wg);
           wg = wg.shiftAdd("yep");
           System.out.println(index+"\t"+wg.length()+"\t"+wg);
       }
    }
}
