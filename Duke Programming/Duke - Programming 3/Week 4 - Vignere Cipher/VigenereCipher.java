import edu.duke.*;
import java.util.*;

public class VigenereCipher {
    CaesarCipher[] ciphers;
    
    public VigenereCipher(int[] key) { //constructor -- intializes ciphers array (of CaesarCipher objects)
        ciphers = new CaesarCipher[key.length]; //initializes ciphers array to length of key
        for (int i = 0; i < key.length; i++) {
            ciphers[i] = new CaesarCipher(key[i]); //creates a new CaesarCipher object for each letter position in key
        }
    }
    
    public String encrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0; //set index to 0
        for (char c : input.toCharArray()) { //iterates of String as an Array of chars
            int cipherIndex = i % ciphers.length; //sets current index in cipher
            CaesarCipher thisCipher = ciphers[cipherIndex]; //create CaesarCipher object using mapped key at current index
            answer.append(thisCipher.encryptLetter(c)); //encrypts current letter with current caesarCipher obj
            i++; //increment current index
        }
        return answer.toString();
    }
    
    public String decrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0; //set current index to 0
        for (char c : input.toCharArray()) {
            int cipherIndex = i % ciphers.length; //get current index
            CaesarCipher thisCipher = ciphers[cipherIndex]; //create CC object using mapped key at current index
            answer.append(thisCipher.decryptLetter(c)); //decrypt letter based on CC obj
            i++;
        }
        return answer.toString();
    }
    
    public String toString() {
        return Arrays.toString(ciphers);
    }
    
}
