//GladLib
//2017 Christopher Mogush

import edu.duke.*;
import java.util.*;

public class GladLib {
    private ArrayList<String> adjectiveList;
    private ArrayList<String> nounList;
    private ArrayList<String> colorList;
    private ArrayList<String> countryList;
    private ArrayList<String> animalList;
    private ArrayList<String> timeList;
    private ArrayList<String> verbList;
    private Random myRandom;
    
    private String dataSourceURL = "http://dukelearntoprogram.com";
    private String dataSourceDirectory = "data";
    
    public GladLib(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLib(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source){
        adjectiveList = readIt(source+"/adjective.txt");
        nounList = readIt(source+"/noun.txt");
        colorList = readIt(source+"/color.txt");
        countryList = readIt(source+"/country.txt");
        nameList = readIt(source+"/name.txt");
        animalList = readIt(source+"/animal.txt");
        timeList = readIt(source+"/time.txt");
        verbList = readIt(source+"/verb.txt");
    }
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    private String getSubstitute(String label){
        if(label.equals("adjective")){
            return randomFrom(adjectiveList);
        }
        if(label.equals("noun")){
            return randomFrom(nounList);
        }
        if(label.equals("color")){
            return randomFrom(colorList);
        }
        if(label.equals("country")){
            return randomFrom(countryList);
        }
        if(label.equals("name")){
            return randomFrom(nameList);
        }
        if(label.equals("animal")){
            return randomFrom(animalList);
        }
        if(label.equals("timeframe")){
            return randomFrom(timeList);
        }
        if(label.equals("verb")){
            return randomFrom(verbList);
        }
        return "**UNKNOWN**";
    }
}
