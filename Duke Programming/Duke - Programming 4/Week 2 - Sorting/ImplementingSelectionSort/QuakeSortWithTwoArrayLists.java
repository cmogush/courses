//2017 Christopher Mogush

import java.util.*;

public class QuakeSortWithTwoArrayLists {
    public ArrayList<QuakeEntry> sortByMagnitude(ArrayList<QuakeEntry> in){
        //out starts as empty ArrayList
        ArrayList<QuakeEntry> out = new ArrayList<QuakeEntry>();
        while(!in.isEmpty()){
            //Find smallest element
            QuakeEntry minElement = getSmallestMagnitude(in);
            in.remove(minElement);
            out.add(minElement);
        }
        return out;
    }
    
    public QuakeEntry getSmallestMagnitude(ArrayList<QuakeEntry> quakes){
        QuakeEntry min = quakes.get(0);
        for(QuakeEntry q : quakes){
            if(q.getMagnitude() < min.getMagnitude()){
                min = q;
            }
        }
        return min;
    }
    
    public void testSort(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        list = sortByMagnitude(list);
        for(QuakeEntry qe : list){
            System.out.println(qe);
        }
        System.out.println("read data for "+list.size());
    }
}
