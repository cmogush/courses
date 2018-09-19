//Caesar Cipher
//2017 Christopher Mogush

import edu.duke.*;
import java.io.*;
import java.util.Scanner;

public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    public CaesarCipher(int key) { //Constructor
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
    }
    
    public String decrypt(String msg) {
        //make a StringBuilder with message (encrypted)
        StringBuilder decryptedMsg = new StringBuilder(msg);
        //write down the alphabet
        String upMsg = msg.toUpperCase();
        
        //count from 0 to < length of encrypted, (call it i)
        for (int i = 0; i < msg.length(); i++){
            char currChar = msg.charAt(i); //set current character to currChar 
            //setup currChar to be encrypted
            Boolean origLowerCase = Character.isLowerCase(currChar); //checks whether currChar is upperCase already
            currChar = Character.toUpperCase(currChar); //sets currChar to upper case
            char decryptedChar = currChar; //set encryptedChar to currChar
            int idx = shiftedAlphabet.indexOf(currChar); //get index of currChar in alphabet
            if(idx != -1){ //if currChar is found in the alphabet
                decryptedChar = alphabet.charAt(idx); //set encryptedChar to same pos in encrypted alphabet
                if(origLowerCase){
                    decryptedChar = Character.toLowerCase(decryptedChar); //return to lower case if originally lc
                }
                decryptedMsg.setCharAt(i, decryptedChar); //updated the encrypted message with the encryptedChar
            }
        }
        //return encrypted message
        String output = decryptedMsg.toString();
        return output;
    }
    
    public void testFileEncrypt(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        System.out.println("Enter a cipher key (1-26) to encode message: ");
        Scanner scan = new Scanner(System.in);
        int key = scan.nextInt();
        String encryptedMessage = encrypt(message);
        String encrypted = encrypt(message);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    
    public void testEncrypt() {
        //input a message to encrypt
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a message to encrypt: ");
        String message = scan.nextLine();
        //input a key
        System.out.println("Enter a cipher key (1-26) to encode message: ");
        int key = scan.nextInt();
        String encryptedMessage = encrypt(message);
        System.out.println("The encrypted message is: " + encryptedMessage);
    }
    
    public void testEncryptTwoKeys() {
        //input a message to encrypt
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a message to encrypt: ");
        String message = scan.nextLine();
        //input a key
        System.out.println("Enter a cipher key1 (1-26) to encode message: ");
        int key1 = scan.nextInt();
        System.out.println("Enter a cipher key2 (1-26) to encode message: ");
        int key2 = scan.nextInt();
        String encryptedMessage = encryptTwoKeys(message, key1, key2);
        System.out.println("The encrypted message is: " + encryptedMessage);
    }
    
    public String encryptTwoKeys(String msg, int key1, int key2){
        //make a StringBuilder with message (encrypted)
        StringBuilder encryptedMsg = new StringBuilder(msg);
        //write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String upMsg = msg.toUpperCase();
        //compute shited alphabet
        String keyOneAlphabet = alphabet.substring(key1) + alphabet.substring(0, key1);
        String keyTwoAlphabet = alphabet.substring(key2) + alphabet.substring(0, key2);
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
    
    public String encrypt(String msg) {
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
                encryptedChar = shiftedAlphabet.charAt(idx); //set encryptedChar to same pos in encrypted alphabet
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