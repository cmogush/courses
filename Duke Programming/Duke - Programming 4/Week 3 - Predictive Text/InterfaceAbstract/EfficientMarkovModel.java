//2018 Christopher Mogush
//EfficientMarkovModel

/* An efficient version of the Markov model which can use any markov size "n" and uses a 
 * reusable hashmap instead of rebuilding an arraylist for each letter.

/* As this class extends AbstractMarkovModel, it is a sub or child-class, allowing us to
   reuse the fields and methods of the existing class. In this case, myText and myRandom.
   In the case of "follows" from AbstractMarkoveModel, it is over-riden by our new 
   custom method in this class. This is polymorphism. */

import java.util.Random;
import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private int keyLength;
    private HashMap<String, ArrayList<String>> map;
    
    public EfficientMarkovModel(int n) {
        keyLength = n; //markov n
        map = new HashMap<String, ArrayList<String>>();
    }
    
    public String getRandomText(int numChars){ //generates and returns random text that is numChars long; keyLength is the num of chars to use for predictive text
       //It should predict the next character, by finding all the characters that follow the current character in the training text
       //then randomly picking one of them as the next character.
       //System.out.println("Creating HashMap for: " + myText);
       buildMap(keyLength);
       printHashMapInfo();
       StringBuffer sb = new StringBuffer();
       int index = myRandom.nextInt(myText.length() - keyLength);
       String key = myText.substring(index, index + keyLength);
       sb.append(key);
       for(int k = 0; k < numChars - keyLength ; k++){ 
           ArrayList<String> follows = getFollows(key); //may return empty ArrayList
           if(follows.size() == 0){ break; } //if empty ArrayList, break the loop
           index = myRandom.nextInt(follows.size());
           String next = follows.get(index);
           sb.append(next);
           key = key.substring(1) + next;
           //get new list from previously found char
       }
       map.clear();
       return sb.toString();
    }
    
    public ArrayList<String> getFollows(String key){ //key is the set of chars
        ArrayList<String> follows = map.get(key); //may return empty ArrayList
        return follows;
    }
    
     public void buildMap(int keyLength){
        //Be sure to handle the case where there may not be a follow character. 
        //If that key is not in the HashMap yet, then it should be put in mapped to an empty ArrayList
        //Build the map at the beginning, from the training text
        int index = 0;
        String key = myText.substring(index, index + keyLength);
        //System.out.println("Key: " + key);
        for(int k = 0; k < myText.length() - keyLength + 1; k++){ 
           //find the value after the key
           if(!map.containsKey(key)){ //if map doesn't already contain the key, create an empty ArrayList
               ArrayList<String> follows = new ArrayList<String>();
               map.put(key, follows);
               //System.out.println(" creating empty ArrayList for value: " + key);
           }
           if(index + keyLength >= myText.length()) { break; }//break if on last index
           String next = myText.substring(index + keyLength, index + keyLength + 1);
           map.get(key).add(next);
           //else{ System.out.println(" " + key + " already has value " + next); }
           key = key.substring(1) + next;
           index += 1;
           //System.out.println("Key: " + key);
           //System.out.println(index);
        }
    }
    
    public void printHashMapInfo(){
        System.out.println("Hashmap size: " + map.size());
        /*
        if(map.size() <= 5){
            System.out.println("Keyset: " + map.keySet());
            System.out.println("Values: " + map.values());
        }
        //find size of largest value
        */
        int largest = 0;
        if(map.size() > 0){ //failsafe
            for(String s : map.keySet()){
                if(map.get(s).size() > largest){
                    largest = map.get(s).size();
                }
            }
        }
        System.out.println("Printing largest ArrayLists, of size: " + largest);
        /*
        //print keys that have largest value
        for(String s : map.keySet()){ //processes each key in turn
            //System.out.println("ArrayList: " + s);
            //System.out.println(map.get(s));
            if(map.get(s).size() == largest){
                System.out.println("ArrayList: " + s);
                System.out.print("Values: ");
                for( String al : map.get(s) ){
                    System.out.print(al + " ");
                }
            }  
        }
        */
    }
    
    public String toString(){
        return "Efficient Markov order " + keyLength;
    }
}
