//2018 Christopher Mogush
//MarkovWordOne

//implements the interface: IMarkovModel

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
        myRandom = new Random();
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+"); // \\s is a regular expression, represents 1 or occurances of any white space char
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - 1);
        String key = myText[index];
        sb.append(key); //add a key from the the myText array, index is chosen at random
        sb.append(" "); //add a space between each word (will result in extra space at end)
        // for loop 
        for(int k=0; k < numWords - 1; k++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size() == 0){ break; }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next); //add the word determined to come next
            sb.append(" "); //add a space between each word (will result in extra space at end)
            key = next;
        }
        return sb.toString().trim(); //remove the extra space with .trim() method
    }
    
    public ArrayList<String> getFollows(String key){ 
        //find all the characters from the private variable myText in MarkovOne that follow key
        //put all these characters into an ArrayList and then return this ArrayList
        ArrayList<String> follows = new ArrayList<String>();
        //iterate over myText
        int pos = 0; //start at index 0
        for(int k=0; k < myText.length - 1; k++){ //go up until last char, - 1 for markov ONE
           //find match using k as start index
           int index = myText.indexOf(key, pos); //find the key and set the index to start there
           if(index < myText.length() - key.length() && index != -1){ //checks to make sure index is within bounds
               String s = myText.substring(index + key.length(), index + key.length() + 1); //set 's' to char at foudn index
               follows.add(s); //add 's' string to follows arrayList
               pos = index + 1; //set next index to 1 after the current position
           }
           if(index == -1){break;}
           //System.out.println("Key: " + key + " index " + index + " " + follows);
        }
        return follows;
    }
}
