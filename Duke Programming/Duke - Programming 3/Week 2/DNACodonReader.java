//2017 Christopher Mogush
//DNACodonReader

import edu.duke.*;
import org.apache.commons.csv.*;
import java.util.Scanner;
import java.io.*;
import java.util.*;
import org.apache.commons.csv.*;

public class DNACodonReader {
    private HashMap<String, Integer> map = new HashMap<String, Integer>();
    
    public DNACodonReader(){
        map.clear();
    }
    
    public void tester(){
        //prompts the user for a file that contains a DNA strand (could be upper or lower case letters in the file 
        FileResource fr = new FileResource();
        String dna = fr.asString();
        //convert them all to uppercase, since case should not matter). 
        dna.toUpperCase();
        dna = dna.trim();
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter range start: ");
        int start = reader.nextInt();
        System.out.println("Enter range end: ");
        int end = reader.nextInt();
        System.out.println("Position 0: ");
        testerExtension(start, end, 0, dna);
        System.out.println("Position 1: ");
        testerExtension(start, end, 1, dna);
        System.out.println("Position 2: ");
        testerExtension(start, end, 2, dna);
    } 
    
    public void testerExtension(int start, int end, int frame, String dna){
        //Then for each of the three possible reading frames, 
        buildCodonMap(frame, dna);
        //this method builds a HashMap of codons to their number of occurrences in the DNA strand
        //prints the total number of unique codons in the reading frame
        System.out.println("Total number of unique codons: " + map.size());
        //prints the most common codon and its count
        System.out.println("The most common codon: " + getMostCommonCodon() + " count: " + map.get(getMostCommonCodon()));
        System.out.println("Print codons in range? (y/n): ");
        Scanner scan = new Scanner(System.in);
        String answer = scan.nextLine();
        if(answer.contains("y")){
            printCodonCounts(start, end);
        }
        System.out.println("Print 'unique' codons? (y/n): ");
        answer = scan.nextLine();
        if(answer.contains("y")){
            System.out.println(map.keySet());
        }
    }
    
    public void buildCodonMap(int start, String dna){
        //build a new map of codons mapped to their counts from the string dna with the 
        //reading frame with the position start (a value of 0, 1, or 2)
        map.clear(); //clear the hashmap of previous entries
        String dnaNew = dna.substring(start, dna.length());
        int length = dnaNew.length() - (dnaNew.length() % 3); 
        for(int x = 0; x < length; x = x + 3){
            String codon = dnaNew.substring(x, x+3);
            if (map.keySet().contains(codon)){
                map.put(codon, map.get(codon) + 1);
            }
            else {
                map.put(codon, 1);
            }
            //System.out.println(codon);
        }
    }
    
    public String getMostCommonCodon(){
    //returns a String, the codon in a reading frame that has the largest count. 
    //If there are several such codons, return any one of them. This method assumes the 
    //HashMap of codons to counts has already been built
    int count = 0;
    String mostCommon = "";
    for (String s : map.keySet()) {
        if(map.get(s) > count){
            mostCommon = s;
            count = map.get(s);
        }
    } 
    return  mostCommon;
    }
    public void printCodonCounts(int start, int end){
        //prints all the codons in the HashMap along with their counts if their count is between start and end, inclusive
        //prints the codons and their number of occurrences for those codons.. 
            //..whose number of occurrences in this reading frame are between two numbers inclusive  
        System.out.println("Codons between start of " + start + " and end of " + end);
        for (String s : map.keySet()){
            // process each key in turn 
        if(map.get(s) >= start && map.get(s) <= end){
            System.out.println(s + " " + map.get(s));
        }
        }
    }
}
