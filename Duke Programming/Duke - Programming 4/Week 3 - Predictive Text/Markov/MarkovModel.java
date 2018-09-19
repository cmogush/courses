//2018 Christopher Mogush
//MarkovModel

import java.util.Random;
import java.util.ArrayList;

public class MarkovModel {
    private String myText;
    private Random myRandom;
    
    public MarkovModel() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){ //Using this method will allow you to generate the same random text each time
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){ //The String s is used to initialize the training text
        myText = s.trim(); //method eliminates leading and trailing spaces
    }
    
    public String getRandomText(int numChars, int keyLength){ //generates and returns random text that is numChars long; keyLength is the num of chars to use for predictive text
       //It should predict the next character, by finding all the characters that follow the current character in the training text
       //then randomly picking one of them as the next character.
       StringBuffer sb = new StringBuffer();
       int index = myRandom.nextInt(myText.length() - keyLength);
       String key = myText.substring(index, index + keyLength);
       sb.append(key);
       for(int k=0; k < numChars - keyLength ; k++){ 
           ArrayList<String> follows = getFollows(key); 
           if(follows.size() == 0){break;} //failsafe
           index = myRandom.nextInt(follows.size());
           String next = follows.get(index);
           sb.append(next);
           key = key.substring(1) + next;
           //get new list from previously found char
       }
        
       return sb.toString();
    }
    
    public ArrayList<String> getFollows(String key){ //key can be multiple chars
        //find all the characters from the private variable myText in MarkovOne that follow key
        //put all these characters into an ArrayList and then return this ArrayList
        ArrayList<String> follows = new ArrayList<String>();
        //iterate over myText
        int pos = 0;
        for(int k=0; k < myText.length() - key.length(); k++){
           //find match using k as start index
           int index = myText.indexOf(key, pos);
           if(index < myText.length() - key.length() && index != -1){
               String s = myText.substring(index + key.length(), index + key.length() + 1);
               follows.add(s);
               pos = index + 1;
           }
           if(index == -1){break;}
           //System.out.println("Key: " + key + " index " + index + " " + follows);
        }
        return follows;
    }
}
