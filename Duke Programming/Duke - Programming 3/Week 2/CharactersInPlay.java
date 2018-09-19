//CharactersInPlay
//Program to determine character with most speaking parts
//2017 Christopher Mogush

import java.util.*;
import edu.duke.*;
import java.util.Scanner;

public class CharactersInPlay {
    private ArrayList<String> myWords; //used to store names
    private ArrayList<Integer> myFreqs; //used to count occurances
    
    public void update(String person){
        //update the two ArrayLists
        //adding the character’s name if it is not already there
        int index = myWords.indexOf(person); 
        if(index == -1){
            myWords.add(person);
            //counting this line as one speaking part for this person
            myFreqs.add(1);
        }
        else {
            int value = myFreqs.get(index);
            myFreqs.set(index, value+1);
        }
        
    }
    
    public CharactersInPlay(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findAllCharacters(){
        //identify a speaking part by reading the file line-by-line
        //finding the location of the first period on the line
        //assume that everything up to the first period is the name of a character
        //count how many times that occurs in the file
        //print those characters that appear more often than others
        
        //get words at start before period, pass as fileResource
        FileResource fr = new FileResource();
        
        for(String line : fr.lines()){
            line = line.toLowerCase();
            if(line.indexOf('.') != -1){
                String name = line.substring(0,line.indexOf('.'));
                update(name);
            }
        }
        //use WordFrequencies class to find most occuring
    }
    
    private void charactersWithNumParts(int num1, int num2){
        //assume num1 should be less than or equal to num2
        //print out the names of all those characters that have exactly number speaking parts
        //where number is greater than or equal to num1 and less than or equal to num2
        System.out.println("Name" + " - " + "No. Speaking Parts");
        for(int k = 0; k < myWords.size(); k++){
            if(myFreqs.get(k) >= num1 && myFreqs.get(k) <= num2){ //threshold for num of speaking parts
                System.out.println(myWords.get(k) + " - " + myFreqs.get(k));
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
       findAllCharacters();
       //print out the main character
       //freq of speaking parts
       System.out.println("Name" + " - " + "No. Speaking Parts");
       for(int k = 0; k < myWords.size(); k++){
           if(myFreqs.get(k) > 5){ // n > is threshold for num of speaking parts
               System.out.println(myWords.get(k) + " - " + myFreqs.get(k));
            }
       }
       int index = findIndexOfMax();
       System.out.println("# unique characters: " + myWords.size());
       System.out.println("The most frequent character is: " + myWords.get(index) + "\t" + "count: " + myFreqs.get(index));
    }
    
    public void testRange(){
        findAllCharacters();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a minimum threshold: ");
        int min = scan.nextInt();
        System.out.println("Enter a maximum threshold: ");
        int max = scan.nextInt();
        charactersWithNumParts(min, max); 
    }
}
