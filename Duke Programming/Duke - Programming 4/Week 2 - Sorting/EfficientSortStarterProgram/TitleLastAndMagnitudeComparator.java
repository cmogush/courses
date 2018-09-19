//2017 Christopher Mogush
//TitleLastAndMagnitudeComparator 

import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2){
        //should compare last word in title of q1 and q2
        //if q1 comes first, return negative
        //if q2 comes after, return positive
        //q1 if less returns negative, positive if more
        String title1 = q1.getInfo();
        String lastWord1 = title1.substring(title1.lastIndexOf(" ")+1);
        String title2 = q2.getInfo();
        String lastWord2 = title2.substring(title2.lastIndexOf(" ")+1);
        
        //use magnitude for tie breakers (returns 0)
        if(lastWord1.compareTo(lastWord2) == 0){
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
        return lastWord1.compareTo(lastWord2);
    }
}
