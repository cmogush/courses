//2017 Christopher Mogush
//MarkovOne

import java.util.Random;
import java.util.ArrayList;

public class MarkovOne extends AbstractMarkovModel {
        
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
    
    public String toString(){
        return "Markov order One ";
    }
}
