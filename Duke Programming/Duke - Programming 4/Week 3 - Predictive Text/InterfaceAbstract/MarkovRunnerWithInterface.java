//2018 Christopher Mogush
//MarkovRunnerWithInterface

// a class designed to create Markov objects, and test them with text

import edu.duke.*; 
import java.util.Scanner;

public class MarkovRunnerWithInterface {     
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Set Random seed: ");
        int seed = sc.nextInt();
        
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        System.out.println("Set Markov N: ");
        int n = sc.nextInt();
        MarkovModel mThree = new MarkovModel(n);
        runModel(mThree, st, size, seed);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);

    }
    
    public void testHashMap() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //String st = "yes-this-is-a-thin-pretty-pink-thistle";
        int size = 1000;
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Set Random seed: ");
        int seed = sc.nextInt();
        
        System.out.println("Set Markov N: ");
        int n = sc.nextInt();
        
        //int size = 50;
        EfficientMarkovModel mEfficient = new EfficientMarkovModel(n);
        runModel(mEfficient, st, size, seed);
    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println();
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
    
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void compareMethods(){ //compare normal vs efficient methods
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //String st = "yes-this-is-a-thin-pretty-pink-thistle";
        int size = 1000;
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Set Random seed: ");
        int seed = sc.nextInt();
        
        System.out.println("Set Markov N: ");
        int n = sc.nextInt();
        
        long timestamp1 = System.nanoTime();
        MarkovModel mModel = new MarkovModel(n);
        
        
        runModel(mModel, st, size, seed);
        
        long timestamp2 = System.nanoTime();
        
        EfficientMarkovModel mEfficient = new EfficientMarkovModel(n);
        runModel(mEfficient, st, size, seed);
        
        long timestamp3 = System.nanoTime();
        
        System.out.println("Time to calculate regular Markov: " + ((timestamp2 - timestamp1)/1000000) + " million nanoseconds");
        System.out.println("Time to calculate efficient Markov: " + ((timestamp3 - timestamp2)/1000000) + " million nanoseconds");
    }
}
