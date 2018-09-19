//2018 Christopher Mogush
//MarkovFour

import java.util.Random;
import java.util.ArrayList;

public class MarkovFour extends AbstractMarkovModel {
    
    public String getRandomText(int numChars){ //generates and returns random text that is numChars long; keyLength is the num of chars to use for predictive text
       //It should predict the next character, by finding all the characters that follow the current character in the training text
       //then randomly picking one of them as the next character.
       StringBuffer sb = new StringBuffer();
       int index = myRandom.nextInt(myText.length() - 4);
       String key = myText.substring(index, index + 4);
       sb.append(key);
       for(int k=0; k < numChars - 4 ; k++){ 
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
    
    public String toString(){
        return "Markov order Four ";
    }
}
