//CaeserDecrypt
//2017 Christopher Mogush

import edu.duke.*;
import java.io.*;
import java.util.Scanner;

public class CaesarDecrypt {    
    public void testDecryptWithTwoKeys(){
       String alpha = "abcdefghijklmnopqrstuvwxyz";
       FileResource file = new FileResource(); //get file
       String msg = file.asString(); //convert it to String
       StringBuilder oddZip =  new StringBuilder(); //sb to hold even letters
       StringBuilder evenZip = new StringBuilder(); //sb to hold odd letters
       int count = 0; //counter
        //Split Msg into even and odd strings
       for(int k = 1; k < msg.length(); k++){
            if(k % 2 == 1){
                oddZip = oddZip.append(msg.charAt(k - 1));
            }
            else{
                evenZip = evenZip.append(msg.charAt(k - 1));
            }
       }
       System.out.println("Encrypted zipped strings are: "); 
       String oddZipStr = oddZip.toString();
       System.out.println(oddZipStr);
        
       String evenZipStr = evenZip.toString();
       System.out.println(evenZipStr);
        
       //now run checks on even zipped string
       Scanner scan = new Scanner(System.in);
       System.out.println("Enter key1: ");
       int key1 = scan.nextInt();
       System.out.println("Enter key2: ");
       int key2 = scan.nextInt();
       
       StringBuilder oddDecrypt = new StringBuilder(decryptCaesarWithKey(oddZipStr, key1)); //oddZipStr
       StringBuilder evenDecrypt = new StringBuilder(decryptCaesarWithKey(evenZipStr, key2)); //evenZipStr
        
       System.out.println("Decrypted zipped strings are: ");
       String oddDecryptStr = oddDecrypt.toString();
       System.out.println(oddDecryptStr);
        
       String evenDecryptStr = evenDecrypt.toString();
       System.out.println(evenDecryptStr);
        
        //rezip strings
       StringBuilder fullMsg = new StringBuilder();
       for(int k = 0; k < oddZipStr.length(); k++){
                fullMsg = fullMsg.append(oddDecryptStr.charAt(k));
            if(k < evenZipStr.length()){
                fullMsg = fullMsg.append(evenDecryptStr.charAt(k));
            }
       }
       String decryptedMsg = fullMsg.toString();
       System.out.println("Decrypted Message Is: ");
       System.out.println(decryptedMsg);
    }
    
    public void testDecryptCaesarTwoKeysGiveLetter(){ //NOTE!!! NEED TO LOOK AT EVERY OTHER LETTER WHEN DECRYPTING!
       String alpha = "abcdefghijklmnopqrstuvwxyz";
       FileResource file = new FileResource(); //get file
       String msg = file.asString(); //convert it to String
       StringBuilder oddZip =  new StringBuilder(); //sb to hold even letters
       StringBuilder evenZip = new StringBuilder(); //sb to hold odd letters
       int count = 0; //counter
        //Split Msg into even and odd strings
       for(int k = 1; k < msg.length(); k++){
            if(k % 2 == 1){
                oddZip = oddZip.append(msg.charAt(k - 1));
            }
            else{
                evenZip = evenZip.append(msg.charAt(k - 1));
            }
       }
       System.out.println("Encrypted zipped strings are: "); 
       String oddZipStr = oddZip.toString();
       System.out.println(oddZipStr);
        
       String evenZipStr = evenZip.toString();
       System.out.println(evenZipStr);
        
        //now run checks on even zipped string
       StringBuilder oddDecrypt = new StringBuilder(decryptCaesar(oddZipStr)); //oddZipStr
       StringBuilder evenDecrypt = new StringBuilder(decryptCaesar(evenZipStr)); //evenZipStr
        
       System.out.println("Decrypted zipped strings are: ");
       String oddDecryptStr = oddDecrypt.toString();
       System.out.println(oddDecryptStr);
        
       String evenDecryptStr = evenDecrypt.toString();
       System.out.println(evenDecryptStr);
        
        //rezip strings
       StringBuilder fullMsg = new StringBuilder();
       for(int k = 0; k < oddZipStr.length(); k++){
                fullMsg = fullMsg.append(oddDecryptStr.charAt(k));
            if(k < evenZipStr.length()){
                fullMsg = fullMsg.append(evenDecryptStr.charAt(k));
            }
       }
       String decryptedMsg = fullMsg.toString();
       System.out.println("Decrypted Message Is: ");
       System.out.println(decryptedMsg);
    }
    
    public void testDecryptCaesarWithKey(){
       Scanner scan = new Scanner(System.in);
       FileResource file = new FileResource(); //get file
       String msg = file.asString(); //convert it to String
       System.out.println("Enter key: ");
       int key = scan.nextInt();
       System.out.println(decryptCaesarWithKey(msg, key));
    }
    
    public String decryptCaesarWithKey(String msg, int key){
        key = 26 - key;
        //make a StringBuilder with message (encrypted)
        StringBuilder decryptedMsg = new StringBuilder(msg);
        //write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String upMsg = msg.toUpperCase();
        //compute shited alphabet
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        //count from 0 to < length of encrypted, (call it i)
        for (int i = 0; i < msg.length(); i++){
            char currChar = msg.charAt(i); //set current character to currChar 
            //setup currChar to be encrypted
            Boolean origLowerCase = Character.isLowerCase(currChar); //checks whether currChar is upperCase already
            currChar = Character.toUpperCase(currChar); //sets currChar to upper case
            char decryptedChar = currChar; //set encryptedChar to currChar
            int idx = alphabet.indexOf(currChar); //get index of currChar in alphabet
            if(idx != -1){ //if currChar is found in the alphabet
                decryptedChar = shiftedAlphabet.charAt(idx); //set encryptedChar to same pos in encrypted alphabet
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
    
    public String decryptCaesar(String msg){
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int[] alphaCount = countChars(msg); //count num of occurances of each char
        int highestValue = indexOfMax(alphaCount); //determine which # in the array is the most occuring char
        
        //identify which letter is the most occuring
        char mostOccuring = alpha.charAt(highestValue);
        System.out.println("The most occuring character in the message is: " + mostOccuring);
        
        //set letter to compare to
        String decryptAlpha = getDecrypter(mostOccuring);
        
        //decode message
        String decryptedMsg = decrypt(msg, decryptAlpha);
        return decryptedMsg;
    }
    
    public void testDecryptSingleCaesar(){
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        FileResource file = new FileResource(); //get file
        String msg = file.asString(); //convert it to String
        int[] alphaCount = countChars(msg); //count num of occurances of each char
        int highestValue = indexOfMax(alphaCount); //determine which # in the array is the most occuring char
        
        //identify which letter is the most occuring
        char mostOccuring = alpha.charAt(highestValue);
        System.out.println("The most occuring character in the message is: " + mostOccuring);
        
        //set letter to compare to
        String decryptAlpha = getDecrypter(mostOccuring);
        
        //decode message
        String decryptedMsg = decrypt(msg, decryptAlpha);
        System.out.println("Decrypted message is: ");
        System.out.println(decryptedMsg);
    }
    
    public String getDecrypter(int mostOccuring){
        //set letter to compare to
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the char assumed to be the most occuring in the language: ");
        String ch = scan.next();
        String lowerCh = ch.toLowerCase(); //set ch to lowercase
        System.out.println("You have entered: " + lowerCh);
        String decryptAlpha = shiftedAlpha(mostOccuring, lowerCh);
        return decryptAlpha;
    }
    
    public String decrypt(String msg, String shiftedAlphabet) {
        shiftedAlphabet = shiftedAlphabet.toUpperCase();
        //make a StringBuilder with message (encrypted)
        StringBuilder decryptedMsg = new StringBuilder(msg);
        //write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
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
    
    public String shiftedAlpha(int mostOccuring, String lowerCh){
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        String adjAlpha = "abcdefghijklmnopqrstuvwxyz";
        //compare the two letters
        int shift = alpha.indexOf(mostOccuring) - alpha.indexOf(lowerCh);
        //g - e = 2
        //shift alphabet 2 to the left
        if(shift > 0){
            //System.out.println("We will shift the alphabet: " + shift + " letters to the left");
            adjAlpha = alpha.substring(shift, alpha.length()) + alpha.substring(0, shift);
        }
        //c - e = -2
        //shift alphabet 2 to right
        if(shift < 0){
            //shift = shift * -1; //convert to positive num
            //System.out.println("We will shift the alphabet: " + shift + " letters to the right");
            shift = 26 + shift;
            adjAlpha = alpha.substring(shift, alpha.length()) + alpha.substring(0, shift);
        }
        
        //adjust alphabet so that they align
        System.out.println("Adjusted alphabet: ");
        System.out.println(adjAlpha);
        System.out.println("Key: " + alpha.indexOf(adjAlpha.charAt(0)));
        return adjAlpha;
    }
    
    public void testCountChars(){
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        FileResource file = new FileResource(); //get file
        String msg = file.asString(); //convert it to String
        int[] count = countChars(msg);
        int Idx = indexOfMax(count);
        System.out.println(alpha.charAt(Idx));
    }
    
    public int[] countChars(String message){
        //find the greatest occurring letter
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
    
     public int indexOfMax(int[] values){
        int idxPos = 0;
        int greatestValue = 0;
        for(int k = 0; k < values.length; k++){
            if(values[k] > greatestValue){
            idxPos = k;
            greatestValue = values[k];
            }
        }
        //Then add code to the method testCountWordLengths to call indexOfMax to determine the most common word length
        //For example, calling indexOfMax after calling countWordLengths on the file smallHamlet.txt should return 3.
        //This method returns the index position of the largest element in values. 
        return idxPos;
    }
}
