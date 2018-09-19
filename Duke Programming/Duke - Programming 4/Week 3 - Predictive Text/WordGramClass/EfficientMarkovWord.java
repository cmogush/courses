//2018 Christopher Mogush
//EfficientMarkovWord
//works for any number of words and uses the WordGram class to handle those words
//builds a HashMap to calculate the follows ArrayList for each possible WordGram only once
//uses the HashMap to look at the list of characters following when it is needed

import java.util.*;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> map;
    
    public EfficientMarkovWord(int order){
        myOrder = order;
        myRandom = new Random();
        map = new HashMap<WordGram, ArrayList<String>>();
    }
    
    public void setRandom(int seed){ //Using this method will allow you to generate the same random text each time
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+"); // \\s is a regular expression, represents 1 or more occurances of any white space char
    }
    
    public void buildMap(){
        //Be sure to handle the case where there may not be a follow character. 
        //If that key is not in the HashMap yet, then it should be put in mapped to an empty ArrayList
        //Build the map at the beginning, from the training text
        int index = 0;
        WordGram key = new WordGram(myText, index, myOrder); //start first WordGram at index 0
        for(int k = 0; k < myText.length + 1 - myOrder; k++){ 
           //find the value after the key
           if(!map.containsKey(key)){ //if map doesn't already contain the key, create an empty ArrayList
               ArrayList<String> follows = new ArrayList<String>();
               map.put(key, follows);
               //System.out.println(" creating empty ArrayList for value: " + key);
           }
           
           if(index + myOrder >= myText.length) { break; }//break if on last index
           
           String next = myText[index + key.length()]; //set 'next' to word at found index, after kGram
           map.get(key).add(next); //add the word to the hashmap
           key = key.shiftAdd(next); //shift the key
           index += 1; //increment the index
        }
    }
    
    private int indexOf(String[] words, WordGram target, int start){ //basically acts like indexOf, but for wordgrams
        //This method should return the first position from start that has the wordgram 'target' in the array words 
        //If there is no such match then return -1.
        
        //iterate over the words array
        for(int k=start; k < words.length - myOrder; k++){
            //for every index, check all of the words match the wordgram
            int check = 0;
            for(int w=0; w < myOrder; w++){
                if(words[k+w].equals(target.wordAt(w))){
                    check += 1; //if the word equals the wordgram, check it off
                }
                if(check == myOrder){
                    return k;
                }
            }
        }
        return -1;
    }
    
    public ArrayList<String> getFollows(WordGram kGram){ //key is wordgram
        ArrayList<String> follows = map.get(kGram); //may return empty arraylist
        return follows;
    }
    
    public String getRandomText(int numWords){
        buildMap();
        printHashMapInfo();
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder); //index is set randomly, from the # of words in myText
        WordGram key = new WordGram(myText, index, myOrder); //wordGram is set from random index, and is of size myOrder
        sb.append(key); //key is appended to stringbuilder
        sb.append(" "); //add a space between each word added to sb (will result in extra space at end)
        // for loop 
        for(int k=0; k < numWords - myOrder; k++){
            ArrayList<String> follows = getFollows(key); //find a word that follows the key
            if(follows.size() <= 0){ break; } //safeguard
            index = myRandom.nextInt(follows.size()); //get random index
            String next = follows.get(index); //pull word using random index
            sb.append(next); //add the word
            sb.append(" "); //add a space between each word (will result in extra space at end)
            key = key.shiftAdd(next);//shift the wordgram with the "next" word
        }
        return sb.toString().trim(); //trim() method removes the extra space
    }
    
    public void printHashMapInfo(){
        System.out.println("Hashmap size: " + map.size());
        if(map.size() <= 20){
            System.out.println("Keyset: " + map.keySet());
            System.out.println("Values: " + map.values());
        }
        //find size of largest value
        int largest = 0;
        if(map.size() > 0){ //failsafe
            for(WordGram wg : map.keySet()){
                if(map.get(wg).size() > largest){
                    largest = map.get(wg).size();
                }
            }
        }
        System.out.println("Printing largest ArrayLists, of size: " + largest);
        //print keys that have largest value
        for(WordGram wg : map.keySet()){ //processes each key in turn
            //System.out.println("ArrayList: " + s);
            //System.out.println(map.get(s));
            if(map.get(wg).size() == largest){
                System.out.println("ArrayList: " + wg);
                System.out.println("Values: ");
                for(String al : map.get(wg) ){
                    System.out.println(al + " ");
                }
            }  
        }
    }
}
