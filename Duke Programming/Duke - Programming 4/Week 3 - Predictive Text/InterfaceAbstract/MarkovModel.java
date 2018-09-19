//2018 Christopher Mogush
//MarkovModel

/* a class built to be able to use markov of any size "N", instead of having to build a separate
 * class for each size. As it extends the AbstractMarkovModel, it reuses methods from the parent
 * class. In this case, myText and myRandom */

import java.util.Random;
import java.util.ArrayList;

public class MarkovModel extends AbstractMarkovModel {
    private int keyLength;
    
    public MarkovModel(int n) {
        keyLength = n;
    }

    public String getRandomText(int numChars){ //generates and returns random text that is numChars long; keyLength is the num of chars to use for predictive text
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
    
    public String toString(){
        return "Markov order " + keyLength;
    }
}
