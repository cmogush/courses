//2018 Christopher Mogush
//AbstractMarkovMOdel

/* As this class implements the IMarkovModel Interface, it MUST have the same fields from the interface
 * and implement them. In this case they are setTraining, setRandom, and getRandomText. 
 * 
 * The purpose of using an interface is that it REQUIRES certain methods be implemented
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    abstract public String getRandomText(int numChars);

    protected ArrayList<String> getFollows(String key){ //key is a single char
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
    
    public void setRandom(int seed){ //Using this method will allow you to generate the same random text each time
		myRandom = new Random(seed);
	}
}
