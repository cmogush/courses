//2018 Christopher Mogush
//MarkovWordTwo

//implements the interface: IMarkovModel

import java.util.*;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
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
        int index = myRandom.nextInt(myText.length - 2); //index is set randomly, from the # of words in myText
        String key1 = myText[index]; //key (word) is set from random index
        String key2 = myText[index + 1]; //key2 is word after key1
        sb.append(key1); //key is appended to stringbuilder
        sb.append(" "); //add a space between each word added to sb (will result in extra space at end)
        sb.append(key2); //key is appended to stringbuilder
        sb.append(" "); //add a space between each word added to sb (will result in extra space at end)
        for(int k=0; k < numWords - 2; k++){
            ArrayList<String> follows = getFollows(key1, key2); //find a word that follows the key
            //System.out.println("Words that follow: " + key1 + " " + key2);
            //System.out.println(follows);
            if(follows.size() == 0){ break; } //safeguard in case no words follow the key(s)
            index = myRandom.nextInt(follows.size()); //get random index
            String next = follows.get(index); //pull word using random index
            sb.append(next); //add the word
            sb.append(" "); //add a space between each word (will result in extra space at end)
            key1 = key2;
            key2 = next; //use the word as the next key
        }
        return sb.toString().trim(); //trim() method removes the extra space
    }
    
    public ArrayList<String> getFollows(String key1, String key2){ //method to get the word that follows key1 and key2
        //find all the words from the private variable myText in MarkovWordOne that follow key
        //put all these words into an ArrayList and then return this ArrayList
        ArrayList<String> follows = new ArrayList<String>();
        //iterate over myText
        int pos = 0; //start at index 0
        while(pos < myText.length){ //go up until last word, - 1 for markov ONE
           //find match using k as start index
           int index = indexOf(myText, key1, key2, pos); //find the key and set the index to start there
           if(index == -1){break;} //safeguard
           if(index + key1.length() + key2.length() >= myText.length - 2){break;} //safeguard
           if(index < myText.length - key1.length() - key2.length() && index != -1){ //checks to make sure index is within bounds
               String next = myText[index + 2]; //set 'next' to word at found
               follows.add(next); //add 'next' string to follows arrayList
               pos = index + 1; //set next index to 1 after the current position
           }
           //System.out.println("Key: " + key + " index " + index + " " + follows);
        }
        return follows;
    }
    
    private int indexOf(String[] words, String target1, String target2, int start){ //basically acts like indexOf
        for(int k=start; k < words.length - 1; k++){
            if(words[k].equals(target1) && words[k+1].equals(target2)){
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
