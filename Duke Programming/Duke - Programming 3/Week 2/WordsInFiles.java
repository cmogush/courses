//2017 Christopher Mogush
//WordsInFiles

import edu.duke.*;
import org.apache.commons.csv.*;
import java.util.Scanner;
import java.io.*;
import java.util.*;
import org.apache.commons.csv.*;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
    public WordsInFiles(){
        map.clear();
    }
    
    public void tester(){
        buildWordFileMap();
        int num = maxNumber();
        int count = 0;
        ArrayList<String> wordList = wordInNumFiles(num);
        for(String s : wordList){
            System.out.println(s);
            count += 1;
        }
        System.out.println("The highest # of files a word appears in is: " + num);
        System.out.println("# of words that appear in " + num + " files is: " + count);
        
        
        System.out.println("Enter specific word to check: (type 'n' to cancel)");
        Scanner scan = new Scanner(System.in);
        String answer = scan.nextLine();
        while(!answer.contains("n")){
            printFilesIn(answer);
            System.out.println("Enter another word to check? (type 'n' to cancel");
            answer = scan.nextLine();
        }
    }
    
    public void printFilesIn(String word){
        //prints names of files the word appears in
        for(String s : map.get(word)){
            System.out.println(s);
        }
    }
    
    public ArrayList<String> wordInNumFiles(int number){
        ArrayList<String> wordList = new ArrayList<String>();
        for(String key : map.keySet()){
            if(map.get(key).size() == number && !map.get(key).contains(key)){
                wordList.add(key);
            }
        }
        //returns an ArrayList of words that appear in exactly number files
        return wordList;
    }
    
    public int maxNumber(){
        //first find size of largest key
        int maxNum = 0; //to store size of largest ArrayList
        for(String key : map.keySet()){ //iterate over all keys
            if(map.get(key).size() > maxNum){ //if current ArrayList > maxNum
                maxNum = map.get(key).size(); //set maxNum to the current ArrayList size
            }
        }
        System.out.println("Enter number of files to check: (enter 0 for max number)");
        Scanner scan = new Scanner(System.in);
        int answer = scan.nextInt();
        if(answer != 0){
            return answer;
        }
        return maxNum;
    }
    
    public void buildWordFileMap(){
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        String filename = f.getName();
        //add words to map
        for(String word : fr.words()){ 
            //if Key : ArrayList doesn't have the filename
            map.putIfAbsent(word, new ArrayList<String>());
            //add the filename to Arraylist
            if(map.get(word).indexOf(filename) == -1){
                map.get(word).add(filename);
            }
        }
    }
}
