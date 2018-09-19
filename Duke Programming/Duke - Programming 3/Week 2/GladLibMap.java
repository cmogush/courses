import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
    private HashMap<String, String> catMap = new HashMap<String, String>(); //category map
    private ArrayList<String> seenWords;
    private int numWords = 0;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        map.clear();
        catMap.clear();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        map.put("adjectiveList", new ArrayList<String>(readIt(source+"/adjective.txt")));
        map.put("nounList", new ArrayList<String>(readIt(source+"/noun.txt")));
        map.put("colorList", new ArrayList<String>(readIt(source+"/color.txt")));
        map.put("countryList", new ArrayList<String>(readIt(source+"/country.txt")));
        map.put("nameList", new ArrayList<String>(readIt(source+"/name.txt")));
        map.put("animalList", new ArrayList<String>(readIt(source+"/animal.txt")));
        map.put("timeframeList", new ArrayList<String>(readIt(source+"/timeframe.txt")));
        map.put("verbList", new ArrayList<String>(readIt(source+"/verb.txt")));
        map.put("fruitList", new ArrayList<String>(readIt(source+"/fruit.txt")));
        seenWords = readIt(source+"/seen.txt");
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("country")) {
            return randomFrom(map.get("countryList"));
        }
        if (label.equals("color")){
            return randomFrom(map.get("colorList"));
        }
        if (label.equals("fruit")){
            return randomFrom(map.get("fruitList"));
        }
        if (label.equals("noun")){
            return randomFrom(map.get("nounList"));
        }
        if (label.equals("name")){
            return randomFrom(map.get("nameList"));
        }
        if (label.equals("adjective")){
            return randomFrom(map.get("adjectiveList"));
        }
        if (label.equals("animal")){
            return randomFrom(map.get("animalList"));
        }
        if (label.equals("timeframe")){
            return randomFrom(map.get("timeframeList"));
        }
        if (label.equals("verb")){
            return randomFrom(map.get("verbList"));
        }
        if (label.equals("number")){
        return ""+myRandom.nextInt(50)+5;
        }
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w; //return the word if it's not in < >
        }
        //if it is in < > then...
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String category = w.substring(first+1,last);
        String sub = getSubstitute(category);
        while(seenWords.contains(sub)){ //if it already exists, try the next word 
            sub = getSubstitute(w.substring(first+1,last));
        }
        seenWords.add(sub);
        numWords += 1;
        //add word substring to category HashMap
        if(!category.contains("number")){ //only count words in the catMap
            StringBuilder catList = new StringBuilder(category);
            catList.append("List");
            String catListString = catList.toString();
            catMap.putIfAbsent(category, catListString);
        }
        //
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
        System.out.println("/n");
        System.out.println("Total words replaced: " + numWords);
    }
    
    private String fromTemplate(String source){
        String story = "";
        int numWords = 0;
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    private int totalWordsInMap(){
        //returns the total number of words in all the ArrayLists in the HashMap
        int totalWords = 0;
        for (String list : map.keySet()){
            totalWords += map.get(list).size();
        }
        return totalWords;
    }
    
    private void totalWordsConsidered(){
        int totalWordsCon = 0;
        for(String cat : catMap.keySet()){
           totalWordsCon += map.get(catMap.get(cat)).size(); //only works if all arraylists have the setup: "category" + "List"
        }
        System.out.println("Total # of words considered: " + totalWordsCon);
    }
    
    public void makeStory(){
        seenWords.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n");
        System.out.println("Total # of words in all ArrayLists: " + totalWordsInMap());
        totalWordsConsidered();
    }
   
}
