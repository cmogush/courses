//WordLengths
//2007 Christopher Mogush

import edu.duke.*;
import java.io.*;
import java.util.Scanner;

public class WordLengths {
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
    
    public void testCountWordLengths(){
        FileResource resource = new FileResource();
        System.out.println("Enter length of longest word to check for: ");
        Scanner scan = new Scanner(System.in);
        int maxLength = scan.nextInt();
        int[] counts = new int[maxLength+1];
        countWordLengths(resource, counts, maxLength);
        for (int k = 0; k < maxLength; k++){   
            if(counts[k] > 0){
                System.out.println(k + ": " + counts[k]);
            }
        }
        System.out.println("The word length with the highest # of occurances is: " + indexOfMax(counts));
        //This method should call countWordLengths with a 
        //file and then print the number of words of each length. Test it on the small file smallHamlet.txt 
        //shown below.
    }

    public void countWordLengths(FileResource resource, int[] counts, int maxLength){
        for (String word : resource.words()) {
            int length = word.length() - 2;
            if(Character.isLetter(word.charAt(0))){
                length = length + 1;
            }
            if(Character.isLetter(word.charAt(word.length()-1))){
                length = length + 1;
            }
            
            if(length >= maxLength){
                counts[maxLength] += 1;
            }
            if(length < 0){
                counts[0] += 1;
            }
            else{
                counts[length] += 1;
            }
        }
    //store these counts in array counts
    //For example, after this method executes, counts[k] should contain the number of words of length k.
    //If a word has a non-letter as the first or last character, it should not be counted as part of the word length
    //For any words equal to or larger than the last index of the counts array, count them as the largest size represented in the counts array.
    }
}
