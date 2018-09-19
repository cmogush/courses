//2018 Christopher Mogush
//MarkovRunner

import edu.duke.*;

public class MarkovRunner {
    public void practiceTestHashMap(){
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        EfficientMarkovWord emw = new EfficientMarkovWord(2); 
        emw.setRandom(65);
        runModel(emw, st, 50); 
    }
    
    public void practiceTest(){
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWord mw = new MarkovWord(5); 
        mw.setRandom(844);
        runModel(mw, st, 200);
    }
    
    public void compareMethods(){
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWord mw = new MarkovWord(2); 
        EfficientMarkovWord emw = new EfficientMarkovWord(2);
        mw.setRandom(42);
        emw.setRandom(42);
        long time = System.nanoTime();
        runModel(mw, st, 100);
        time = System.nanoTime() - time;
        System.out.println("Regular MarkovWord time to complete: " + time);
        time = System.nanoTime();
        runModel(emw, st, 100);
        time = System.nanoTime() - time;
        System.out.println("Efficient MarkovWord time to complete: " + time);
    }
    
    public void testHashMap(){
        //FileResource fr = new FileResource(); 
        //String st = fr.asString(); 
        //st = st.replace('\n', ' '); 
        String st = "this is a test yes this is really a test yes a test this is wow";
        EfficientMarkovWord mw = new EfficientMarkovWord(2); 
        mw.setRandom(42);
        runModel(mw, st, 50); 
    }
    
    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWord mw = new MarkovWord(3); 
        mw.setRandom(643);
        runModel(mw, st, 200); 
    } 
    
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    private void printOut(String s){
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
