//CaesarCipherTwo
//2017 Christopher Mogush

import edu.duke.*;
import java.io.*;
import java.util.Scanner;

public class CaesarCipherTwo {
    
    private String alphabet;
    private String keyOneAlphabet;
    private String keyTwoAlphabet;
    
    public CaesarCipherTwo(int key1, int key2) { //Constructor
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        keyOneAlphabet = alphabet.substring(key1) + alphabet.substring(0, key1);
        keyTwoAlphabet = alphabet.substring(key2) + alphabet.substring(0, key2);
    }   
    
    public String encrypt(String msg){
        //make a StringBuilder with message (encrypted)
        StringBuilder encryptedMsg = new StringBuilder(msg);
        String upMsg = msg.toUpperCase();
        //count from 0 to < length of encrypted, (call it i)
        for (int i = 0; i < msg.length(); i++){
            char currChar = msg.charAt(i); //set current character to currChar 
            //setup currChar to be encrypted
            Boolean origLowerCase = Character.isLowerCase(currChar); //checks whether currChar is upperCase already
            currChar = Character.toUpperCase(currChar); //sets currChar to upper case
            char encryptedChar = currChar; //set encryptedChar to currChar
            int idx = alphabet.indexOf(currChar); //get index of currChar in alphabet
            if(idx != -1){ //if currChar is found in the alphabet
                if((i+1) % 2 == 1){ //if odd use key1
                    encryptedChar = keyOneAlphabet.charAt(idx); //set encryptedChar to Char same pos in encrypted alphabet
                }
                if((i+1) % 2 == 0){ //if even use key2
                    encryptedChar = keyTwoAlphabet.charAt(idx); //set encryptedChar to Char same pos in encrypted alphabet
                }
                if(origLowerCase){
                    encryptedChar = Character.toLowerCase(encryptedChar); //return to lower case if originally lc
                }
                encryptedMsg.setCharAt(i, encryptedChar); //updated the encrypted message with the encryptedChar
            }
        }
        //return encrypted message
        String output = encryptedMsg.toString();
        return output;
    }
}