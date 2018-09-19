//2017 Christopher Mogush
//MarkovRunner

import edu.duke.*;
import java.util.Scanner;

public class MarkovRunner {
    public void runMarkovModel() { //generates three sets of randomly generated text using the file read in to choose the random characters from
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' '); //replaces all new lines with spaces
        //st = "this is a test yes this is a test.";
        MarkovModel markov = new MarkovModel();
        markov.setTraining(st); //sets the training text to the file resource "st"
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Set Random Seed: ");
        markov.setRandom(sc.nextInt());
        System.out.println("Set Markov Model: ");
        int model = sc.nextInt();
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500, model);
            printOut(text);
        }
    }public void runMarkovFour() { //generates three sets of randomly generated text using the file read in to choose the random characters from
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' '); //replaces all new lines with spaces
        //st = "this is a test yes this is a test.";
        MarkovFour markov = new MarkovFour();
        markov.setTraining(st); //sets the training text to the file resource "st"
        markov.setRandom(715);
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500, 4);
            printOut(text);
        }
    }
    public void runMarkovOne() { //generates three sets of randomly generated text using the file read in to choose the random characters from
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' '); //replaces all new lines with spaces
        //st = "this is a test yes this is a test.";
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st); //sets the training text to the file resource "st"
        markov.setRandom(365);
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }
    public void runMarkovZero() { //generates three sets of randomly generated text using the file read in to choose the random characters from
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' '); //replaces all new lines with spaces
        MarkovZero markov = new MarkovZero();
        markov.setRandom(1024);
        markov.setTraining(st); //sets the training text to the st file
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }
    
    private void printOut(String s){
    //print out the random text that was generated with around 60 characters per line. 
    //DO NOT CHANGE THIS METHOD
        String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for(int k=0; k < words.length; k++){
			System.out.print(words[k]+ " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}
	
}
