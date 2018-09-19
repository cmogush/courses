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
    
    public void setRandom(int seed){ //Using this method will allow you to generate the same random text each time
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+"); // \\s is a regular expression, represents 1 or more occurances of any white space char
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - 1); //index is set randomly, from the # of words in myText
        String key = myText[index]; //key (word) is set from random index
        sb.append(key); //key is appended to stringbuilder
        sb.append(" "); //add a space between each word added to sb (will result in extra space at end)
        for(int k=0; k < numWords - 1; k++){
            ArrayList<String> follows = getFollows(key); //find a word that follows the key
            //System.out.println("Words that follow: " + key);
            //System.out.println(follows);
            if(follows.size() == 0){ break; } //safeguard
            index = myRandom.nextInt(follows.size()); //get random index
            String next = follows.get(index); //pull word using random index
            sb.append(next); //add the word
            sb.append(" "); //add a space between each word (will result in extra space at end)
            key = next; //use the word as the next key
        }
        return sb.toString().trim(); //trim() method removes the extra space
    }
    
    public ArrayList<String> getFollows(String key){ //method to get the word that follows the key
        //find all the words from the private variable myText in MarkovWordOne that follow key
        //put all these words into an ArrayList and then return this ArrayList
        ArrayList<String> follows = new ArrayList<String>();
        //iterate over myText
        int pos = 0; //start at index 0
        while(pos < myText.length){ //go up until last word, - 1 for markov ONE
           //find match using k as start index
           int index = indexOf(myText, key, pos); //find the key and set the index to start there
           if(index == -1){break;} //safeguard
           if(index + key.length() >= myText.length - 1){break;} //safeguard
           if(index < myText.length - key.length() && index != -1){ //checks to make sure index is within bounds
               String next = myText[index + 1]; //set 'next' to word at found index
               follows.add(next); //add 'next' string to follows arrayList
               pos = index + 1; //set next index to 1 after the current position
           }
           //System.out.println("Key: " + key + " index " + index + " " + follows);
        }
        return follows;
    }
    
    private int indexOf(String[] words, String target, int start){ //basically acts like indexOf
        for(int k=start; k < words.length; k++){
            if(words[k].equals(target)){
                return k; //return index at which you found the word
            }
        }
        return -1;
    }
    
    public void testIndexOf(){
        String words = "This is just a test yes this is a simple test";
        String[] wordArray = words.split("\\s+");
        for(int k = 0 ; k < wordArray.length; k++){
        System.out.println("Index " + k + ": " + wordArray[k]);
        }    
    }
}
