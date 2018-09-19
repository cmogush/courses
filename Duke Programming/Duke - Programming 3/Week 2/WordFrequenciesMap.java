//2017 Christopher Mogush
//WordFrequencies

import edu.duke.*;
import java.util.*;

public class WordFrequenciesMap {
    public void countWords(){
        FileResource fr = new FileResource();
        HashMap <String, Integer> map = new HashMap<String, Integer>();
        int total = 0;
        for(String w : fr.words()){
            w = w.toLowerCase();
            if (map.keySet().contains(w)){
                map.put(w, map.get(w) + 1);
            }
            else {
                map.put(w,1);
            }
        }
        for(String w : map.keySet()){
            int occurences = map.get(w);
            if (occurences > 400){
                System.out.println(occurences + "\t" + w);
            }
        }
    }
}
