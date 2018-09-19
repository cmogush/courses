//2018 Christopher Mogush
//MarkovWord
//works for any number of words and uses the WordGram class to handle those words

import java.util.*;

public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int order){
        myOrder = order;
        myRandom = new Random();
    }
    
    public void setRandom(int seed){ //Using this method will allow you to generate the same random text each time
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+"); // \\s is a regular expression, represents 1 or more occurances of any white space char
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
    
    public ArrayList<String> getFollows(WordGram kGram){ 
        //returns an ArrayList of all the single words that immediately follow an instance of the WordGram 
        //parameter somewhere in the training text. This method should call indexOf to find these matches.
        ArrayList<String> follows = new ArrayList<String>();
        //iterate over myText
        int pos = 0; //start at index 0
        while(pos < myText.length){ //go up until last word, - 1 for markov ONE
           //find match using k as start index
           int index = indexOf(myText, kGram, pos); //find the key and set the index to start there
           if(index == -1){break;} //safeguard
           if(index < myText.length - kGram.length() && index != -1){ //checks to make sure index is within bounds
               String next = myText[index + kGram.length()]; //set 'next' to word at found index, after kGram
               follows.add(next); //add 'next' string to follows arrayList
               pos = index + 1; //set next index to 1 after the current position
           }
           //System.out.println("Key: " + key + " index " + index + " " + follows);
        }
        //System.out.println(kGram);
        //System.out.println(follows);
        return follows;
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder); //index is set randomly, from the # of words in myText
        WordGram key = new WordGram(myText, index, myOrder); //wordGram is set from random index, and is of size myOrder
        sb.append(key); //key is appended to stringbuilder
        sb.append(" "); //add a space between each word added to sb (will result in extra space at end)
        // for loop 
        for(int k=0; k < numWords - myOrder; k++){
            ArrayList<String> follows = getFollows(key); //find a word that follows the key
            if(follows.size() == 0){ break; } //safeguard
            index = myRandom.nextInt(follows.size()); //get random index
            String next = follows.get(index); //pull word using random index
            sb.append(next); //add the word
            sb.append(" "); //add a space between each word (will result in extra space at end)
            key = key.shiftAdd(next);//shift the wordgram with the "next" word
        }
        return sb.toString().trim(); //trim() method removes the extra space
    }
}
