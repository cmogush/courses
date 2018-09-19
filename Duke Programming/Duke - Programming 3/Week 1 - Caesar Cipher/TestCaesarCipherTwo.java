//TestCaesarCipherTwo
//2017 Christopher Mogush

import edu.duke.*;
import java.io.*;
import java.util.Scanner;

public class TestCaesarCipherTwo {
    public void testDecryptTwoKeyMsg(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a message to decrypt: ");
        String msg = scan.nextLine();
        //input a key
        System.out.println("Enter cipher key1 (1-26) to decode message: ");
        int key1 = scan.nextInt();
        System.out.println("Enter cipher key2 (1-26) to decode message: ");
        int key2 = scan.nextInt();
        //create a CaesarCipherTwo object with keys 17 and 3
        CaesarCipherTwo cc = new CaesarCipherTwo(key1, key2);
        //encrypt the String using the CaesarCipherTwo object
        String msgEncrypted = msg;
        //print the encrypted String
        System.out.println(msgEncrypted);
        //split the string in half
        String oddHalf = halfOfString(msgEncrypted, 0);
        String evenHalf = halfOfString(msgEncrypted, 1);
        //decrypt the encrypted Strings using the decrypt method
        oddHalf = breakCaesarCipher(oddHalf, key1);
        evenHalf = breakCaesarCipher(evenHalf, key2);
        //zip together the odd and even halves
        StringBuilder sb = new StringBuilder();
        for(int k = 0; k < oddHalf.length(); k++){
            sb = sb.append(oddHalf.charAt(k));
            if(k < evenHalf.length()){
                sb = sb.append(evenHalf.charAt(k));
            }
            }
       
        String decryptedMsg = sb.toString();
        System.out.println(decryptedMsg);
    }
    
    public void simpleDecryptInputCommonLetter(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a message to decrypt: ");
        String msgEncrypted = scan.nextLine();
        //split the string in half
        String oddHalf = halfOfString(msgEncrypted, 0);
        String evenHalf = halfOfString(msgEncrypted, 1);
        //decrypt the encrypted Strings using the decrypt method
        oddHalf = breakCaesarCipherCommonLetter(oddHalf);
        evenHalf = breakCaesarCipherCommonLetter(evenHalf);
        //zip together the odd and even halves
        StringBuilder sb = new StringBuilder();
        for(int k = 0; k < oddHalf.length(); k++){
            sb = sb.append(oddHalf.charAt(k));
            if(k < evenHalf.length()){
                sb = sb.append(evenHalf.charAt(k));
            }
            }
        String decryptedMsg = sb.toString();
        System.out.println(decryptedMsg);
    }
    
    public void simpleDecryptFileCommonLetter(){
        //read in a file as a String
        FileResource file = new FileResource();
        String frEncrypted = file.asString();
        //split the string in half
        String oddHalf = halfOfString(frEncrypted, 0);
        String evenHalf = halfOfString(frEncrypted, 1);
        //decrypt the encrypted Strings using the decrypt method
        oddHalf = breakCaesarCipherCommonLetter(oddHalf);
        evenHalf = breakCaesarCipherCommonLetter(evenHalf);
        //zip together the odd and even halves
        StringBuilder sb = new StringBuilder();
        for(int k = 0; k < oddHalf.length(); k++){
            sb = sb.append(oddHalf.charAt(k));
            if(k < evenHalf.length()){
                sb = sb.append(evenHalf.charAt(k));
            }
            }
       
        String decryptedMsg = sb.toString();
        System.out.println(decryptedMsg);
    }
    
    public void simpleTestsInput(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a message to encrypt: ");
        String msg = scan.nextLine();
        //input a key
        System.out.println("Enter cipher key1 (1-26) to encode message: ");
        int key1 = scan.nextInt();
        System.out.println("Enter cipher key2 (1-26) to encode message: ");
        int key2 = scan.nextInt();
        //create a CaesarCipherTwo object with keys 17 and 3
        CaesarCipherTwo cc = new CaesarCipherTwo(key1, key2);
        //encrypt the String using the CaesarCipherTwo object
        String msgEncrypted = cc.encrypt(msg);
        //print the encrypted String
        System.out.println(msgEncrypted);
        //split the string in half
        String oddHalf = halfOfString(msgEncrypted, 0);
        String evenHalf = halfOfString(msgEncrypted, 1);
        //decrypt the encrypted Strings using the decrypt method
        oddHalf = breakCaesarCipherCommonLetter(oddHalf);
        evenHalf = breakCaesarCipherCommonLetter(evenHalf);
        //zip together the odd and even halves
        StringBuilder sb = new StringBuilder();
        for(int k = 0; k < oddHalf.length(); k++){
            sb = sb.append(oddHalf.charAt(k));
            if(k < evenHalf.length()){
                sb = sb.append(evenHalf.charAt(k));
            }
            }
       
        String decryptedMsg = sb.toString();
        System.out.println(decryptedMsg);
    }
    
    public void simpleTestsOriginal(){
        //read in a file as a String
        FileResource file = new FileResource();
        String fr = file.asString();
        //create a CaesarCipherTwo object with keys 17 and 3
        CaesarCipherTwo cc = new CaesarCipherTwo(17, 3);
        //encrypt the String using the CaesarCipherTwo object
        String frEncrypted = cc.encrypt(fr);
        //print the encrypted String
        System.out.println(frEncrypted);
        //split the string in half
        String oddHalf = halfOfString(frEncrypted, 0);
        String evenHalf = halfOfString(frEncrypted, 1);
        //decrypt the encrypted Strings using the decrypt method
        oddHalf = breakCaesarCipherCommonLetter(oddHalf);
        evenHalf = breakCaesarCipherCommonLetter(evenHalf);
        //zip together the odd and even halves
        StringBuilder sb = new StringBuilder();
        for(int k = 0; k < oddHalf.length(); k++){
            sb = sb.append(oddHalf.charAt(k));
            if(k < evenHalf.length()){
                sb = sb.append(evenHalf.charAt(k));
            }
            }
       
        String decryptedMsg = sb.toString();
        System.out.println(decryptedMsg);
    }

    public String breakCaesarCipher(String input, int key){
        //create a CaesarCipher object with that key and decrypt the message
        CaesarCipher cc = new CaesarCipher(key);
        String decryptedMsg = cc.decrypt(input);
        return decryptedMsg;
    }
    
    public String breakCaesarCipherCommonLetter(String input){
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
    
    private String halfOfString(String msg, int start){
        //This method should return a new String that is every other character from message 
        //starting with the start position
        StringBuilder sb =  new StringBuilder();
        for (int k = start; k < msg.length(); k+=2){
             sb = sb.append(msg.charAt(k));
        }
        String stringHalf = sb.toString();
        return stringHalf;
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
}
