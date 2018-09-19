//TestCaesarCipher
//2017 Christopher Mogush

import edu.duke.*;
import java.io.*;
import java.util.Scanner;

public class TestCaesarCipher {
    public void simpleTestsInput(){
        //input a message to encrypt
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a message to encrypt: ");
        String msg = scan.nextLine();
        //input a key
        System.out.println("Enter a cipher key (1-26) to encode message: ");
        int key = scan.nextInt();
        //create caesar cipher object with key
        CaesarCipher cc = new CaesarCipher(key);
        //encrypt the String read in using the CaesarCipher object
        String msgEncrypted = cc.encrypt(msg);
        //print the encrypted String
        System.out.println(msgEncrypted);
        System.out.println(breakCaesarCipher(msgEncrypted));
    }
    public void simpleTestsFile(){
        //This method should read in a file as a String
        FileResource file = new FileResource();
        String fr = file.asString();
        //input a key
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a cipher key (1-26) to encode message: ");
        int key = scan.nextInt();
        CaesarCipher cc = new CaesarCipher(key);
        //encrypt the String read in using the CaesarCipher object
        String frEncrypted = cc.encrypt(fr);
        //print the encrypted String
        System.out.println(frEncrypted);
        System.out.println(breakCaesarCipher(frEncrypted));
    }
    
    public String breakCaesarCipher(String input){
        //figure out which key was used to encrypt this message
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int count[] = new int[26];
        count = countChars(input);
        int idxCh = indexOfMax(count);
        int key = idxCh - 4; //4 = e, or most popular char in english
        if(key < 0){ //if key is negative, change to positive
            key = 26 + key;
        }
        System.out.println("The Key is: " + key);
        //create a CaesarCipher object with that key and decrypt the message
        CaesarCipher cc = new CaesarCipher(key);
        String decryptedMsg = cc.decrypt(input);
        return decryptedMsg;
    }
    
    private int indexOfMax(int[] values){
        int idxPos = 0;
        int greatestValue = 0;
        for(int k = 0; k < values.length; k++){
            if(values[k] > greatestValue){
            idxPos = k;
            greatestValue = values[k];
            }
        }
        return idxPos;
    }
    
    private int[] countChars(String message){
        //find the greatest occurring letter
        message = message.toLowerCase();
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int[] count = new int[26];
        for(int k = 0; k < message.length(); k++){
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alpha.indexOf(ch);
            if (dex != -1){
                count[dex] += 1;
            }
        }
        return count;
    }
    //store these counts in array counts
    //For example, after this method executes, counts[k] should contain the number of words of length k.
    //If a word has a non-letter as the first or last character, it should not be counted as part of the word length
    //For any words equal to or larger than the last index of the counts array, count them as the largest size represented in the counts array.
}

