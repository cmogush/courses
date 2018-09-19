//2017 Christopher Mogush
//TitleAndDepthComparator

import java.util.*;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {

    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String title1 = q1.getInfo();
        String title2 = q2.getInfo();
        double depth1 = q1.getDepth();
        double depth2 = q2.getDepth();
        //use depth for tie breakers
        if(title1.compareTo(title2) == 0){ 
            return Double.compare(depth1, depth2);
        }
        return title1.compareTo(title2);
    }
    
}
 