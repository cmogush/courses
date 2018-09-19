
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TO DO
        if(quakeData.size() < howMany){
            howMany = quakeData.size();
        }
        for(int i = 0; i < howMany; i++){
            double closest = 1.23456*10e300d;
            QuakeEntry newLocation = null;
            for(QuakeEntry q : quakeData){
                if(current.distanceTo(q.getLocation()) < closest && !answer.contains(q)){
                    closest = current.distanceTo(q.getLocation());
                    newLocation = q;
                }
            }
            if(newLocation != null){answer.add(newLocation);}
        }
        //This method should find the closest number of howMany earthquakes to the current Location 
        //and return them in an ArrayList of type QuakeEntry. The earthquakes should be in the 
        //ArrayList in order with the closest earthquake in index position 0. 
        //If there are fewer then howMany earthquakes in quakeData, then the ArrayList returned 
        //would be the same size as quakeData.
        return answer;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,3);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
}
