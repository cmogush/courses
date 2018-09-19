//2018 Christopher Mogush
//WordGram

public class WordGram {
    private String[] myWords; //used to store words
    private int myHash; //allows wordgrams to be used as a key with a HashMap
    
    //constructor copies the size number of words from source starting at the position start into a new WordGram
    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size); //copies the StringArray of "source" to "mywords"
    }

    //returns the word in the WordGram at position index
    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }
    
    //returns the length of the WordGram
    public int length(){
        return myWords.length;
    }
    
    //prints a WordGram out, showing all the words in the WordGram on one line separated by spaces
    public String toString(){
        String ret = "";
        for(int k = 0; k < myWords.length; k++){
            ret += myWords[k] + " ";
        }        
        return ret.trim();
    }

    //returns true if two WordGrams are equal and false otherwise
    public boolean equals(Object o) {
        WordGram other = (WordGram) o; //cast the Object 'o' as type WordGram (so it will be treated as a WordGram)
        //compare me to other
        if(this.length() != other.length()) {return false;}
        for(int k = 0; k < myWords.length; k++){
            if(!myWords[k].equals(other.wordAt(k))){return false;}
        }
        return true;
    }

    //should return a new WordGram the same size as the original, consisting of each word shifted down one index
    public WordGram shiftAdd(String word) { 
        WordGram out = new WordGram(myWords, 0, myWords.length);
        // shift all words one towards 0 
        int size = myWords.length;
        for(int k=0; k < myWords.length - 1; k++){
            out.myWords[k] = out.myWords[k+1];
        }
        out.myWords[myWords.length - 1] = word;
        return out;
    }

    public int hashCode(){ //converst the wordgram to be readable by a hashmap
        //return an integer that is a hash code that represents the WordGram
        //String has a method hashCode. 
        //You may want to create a String from the WordGram and use the String hashCode method
        return toString().hashCode();
    }
}