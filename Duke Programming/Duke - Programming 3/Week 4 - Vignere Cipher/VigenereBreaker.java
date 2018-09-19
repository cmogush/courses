import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //takes (encrypted) message
        //whichSlice is the index the slice should start from
        //totalSlices is the length of the key
        StringBuilder encrypted = new StringBuilder(message);
        StringBuilder answer = new StringBuilder();
        for(int i = whichSlice; i < message.length(); i += totalSlices){
            answer.append(encrypted.charAt(i));
        }
        //returns String consisting of every totalSlices-th character from msg
        //starting at the whichSlice-th character
        return answer.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        //ecrypted is msg
        //klength is key length
        //char is mostCommon used char in language
        //GOAL is to find shift for each index in klength
        //use CaesarCracker to getKey()
        CaesarCracker cc = new CaesarCracker(mostCommon); //create cc object using most common letter in lang
        int[] key = new int[klength]; //we know the key length
        //use sliceString to split into individual strings
        //then solve key for each individual string AND save it to the klength index
        for(int i = 0; i < klength; i++){ //start at index 0;
            String slice = sliceString(encrypted, i, klength);
            key[i] = cc.getKey(slice);
        }
        //returns array of each shift of index in key array
        return key;
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> set = new HashSet<String>(); 
        for(String word : fr.words()){
            word = word.toLowerCase();
            set.add(word);
        }
        return set;
    }
    
    public int countWords(String msg, HashSet<String> dictionary){
        msg = msg.toLowerCase();
        String[] splitMsg = msg.split("\\W+");
        int count = 0;
        for(int i = 0; i < splitMsg.length; i++){
            if(dictionary.contains(splitMsg[i])){
                count++;
            }
        }
        return count;
    }
    
    public String keyWord(int[] key){
        StringBuilder alphabet = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        StringBuilder word = new StringBuilder();
        for(int i = 0; i < key.length; i++){
            word.append(alphabet.charAt(key[i]));
        }
        return word.toString();
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        //try all keylengths from 1 to 100
        //find key with highest count of matched words
        int keyLength = 0;
        int highest = 0;
        int key[] = null;
        String answer = null;
        for(int i = 1; i <= 100; i++){
            int currentKey[] = tryKeyLength(encrypted, i, mostCommonCharIn(dictionary));
            VigenereCipher vc = new VigenereCipher(currentKey);
            String decrypted = vc.decrypt(encrypted);
            int countWords = countWords(decrypted, dictionary);
            if(countWords > highest){
                keyLength = i;
                key = currentKey;
                highest = countWords;
                answer = decrypted;
            }
        }
        //return decryption of found key
        String[] splitMsg = answer.split("\\W+");
        int noWords = splitMsg.length;
        float percentMatch = ((float)highest/(float)noWords)*100;
        String keyword = keyWord(key);
        System.out.println("Key length is determined to be: " + keyLength);
        System.out.println("Key array is: " + Arrays.toString(key));
        System.out.println("Key word is: " + keyword);
        System.out.println(highest + " matches out of " + noWords + " total words.");
        System.out.println(percentMatch + "% match.");
        System.out.println();
        return answer;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        //read in information
        HashMap<String, Integer> alphabet = new HashMap<String, Integer>();
        for(String word : dictionary){
            word = word.toLowerCase();
            StringBuilder currentWord = new StringBuilder(word);
            for(int i = 0; i < word.length(); i++){
                String currentChar = Character.toString(word.charAt(i));
                if(alphabet.keySet().contains(currentChar)){
                   alphabet.put(currentChar, alphabet.get(currentChar) + 1);
                }
                else{
                    alphabet.put(currentChar, 1);
                }
            }
        }
        //determine most occuring char
        char answer = 'a';
        int max = 0;
        for(String s : alphabet.keySet()){
            if(alphabet.get(s) > max){
                answer = s.charAt(0);
                max = alphabet.get(s);
            }
        }
        return answer;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
        //"languages" is dictionary of all languages
        //iterate of languages.keySet() and use .get() to lookup dictionary
        //use breakForLanguage and countWords
        //print out decrypted message and what language was determined
        int highest = 0;
        String msg = null;
        String lang = null;
        float percentMatch = 0;
        int count = 0;
        String[] splitMsg = encrypted.split("\\W+");
        int total = splitMsg.length;;
        for(String s : languages.keySet()){ //iterate all languages
            System.out.println("Breaking for " + s);
            String decrypted = breakForLanguage(encrypted, languages.get(s));
            count = countWords(decrypted, languages.get(s));
            if(count > highest){
                msg = decrypted;
                lang = s;
                highest = count;
                System.out.println("Highest updated to: " + highest);
                percentMatch = ((float)count / (float)total) * 100;
            }
            System.out.println();
        }
        System.out.println(msg);
        System.out.println();
        System.out.println("Language: " + lang);
        System.out.println("Percent Match: " + percentMatch + "%");
        System.out.println("Matched words: " + highest);
        System.out.println("Total words in message: " + total);
    }
    
    public void breakVigenere() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        
        System.out.println("Read in dictionaries");
        System.out.println("Danish");
        FileResource danish = new FileResource("dictionaries/Danish");
        languages.put("Danish", readDictionary(danish));
        
        System.out.println("Dutch");
        FileResource dutch = new FileResource("dictionaries/Dutch");
        languages.put("Dutch", readDictionary(dutch));
        
        System.out.println("English");
        FileResource english = new FileResource("dictionaries/English");
        languages.put("English", readDictionary(english));
        
        System.out.println("French");
        FileResource french = new FileResource("dictionaries/French");
        languages.put("French", readDictionary(french));
        
        System.out.println("German");
        FileResource german = new FileResource("dictionaries/German");
        languages.put("German", readDictionary(german));
        
        System.out.println("Italian");
        FileResource italian = new FileResource("dictionaries/Italian");
        languages.put("Italian", readDictionary(italian));
        
        System.out.println("Portuguese");
        FileResource portuguese = new FileResource("dictionaries/Portuguese");
        languages.put("Portuguese", readDictionary(portuguese));
        
        System.out.println("Spanish");
        FileResource spanish = new FileResource("dictionaries/Spanish");
        languages.put("Spanish", readDictionary(spanish));
        
        System.out.println();
        breakForAllLangs(encrypted, languages);
    }
}
