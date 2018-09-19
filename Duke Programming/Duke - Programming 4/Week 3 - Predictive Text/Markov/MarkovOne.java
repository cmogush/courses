//2017 Christopher Mogush
//MarkovFour

import java.util.Random;
import java.util.ArrayList;

public class MarkovOne {
    private String myText;
    private Random myRandom;
    
    public MarkovOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){ //Using this method will allow you to generate the same random text each time
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){ //The String s is used to initialize the training text
        myText = s.trim(); //method eliminates leading and trailing spaces
    }
    
    public String getRandomText(int numChars){ //generates and returns random text that is numChars long
       //It should predict the next character, by finding all the characters that follow the current character in the training text
       //then randomly picking one of them as the next character.
       StringBuilder sb = new StringBuilder();
       sb.append(myText.charAt(myRandom.nextInt(myText.length() - 1)));
       String currentChar = sb.toString();
       for(int k=0; k < numChars -1 ; k++){ 
           ArrayList<String> charsThatFollow = getFollows(currentChar); //key is char at start of  index
           //System.out.println(myText.substring(k, k+1));
           if(charsThatFollow.size() == 0){break;} //failsafe
           //get new list from previously found char
           currentChar = charsThatFollow.get(myRandom.nextInt(charsThatFollow.size()));
           sb.append(currentChar);
       }
        
       return sb.toString();
    }
    
    public ArrayList<String> getFollows(String key){ //key is a single char
        //find all the characters from the private variable myText in MarkovOne that follow key
        //put all these characters into an ArrayList and then return this ArrayList
        ArrayList<String> follows = new ArrayList<String>();
        //iterate over myText
        int pos = 0;
        for(int k=0; k < myText.length() - 1; k++){
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
