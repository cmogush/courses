//2017 Christopher Mogush
//JAVA4 - EarthQuakeClient2

import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        //returns an ArrayList of QuakeEntry’s from quakeData that 
        //meet the requirements of Filter f’s satisfies method
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         

        //Filter f1 = new MagnitudeFilter(4.0, 5.0, "Magnitude");
        //Filter f2 = new DepthFilter(-35000.0, -12000.0, "Depth");
        Location city = new Location(35.42, 139.43);
        //Location city = new Location(39.7392, -104.9903);
        Filter f1 = new DistanceFilter(10000000, city, "Distance");
        Filter f2 = new PhraseFilter("Japan", "end", "Phrase");
        
        ArrayList<QuakeEntry> fList1  = filter(list, f1); 
        ArrayList<QuakeEntry> fList2  = filter(fList1, f2); 
        for (QuakeEntry qe: fList2) { 
            System.out.println(qe);
        } 
        
        System.out.println("read data for "+list.size()+" quakes");
        
        System.out.println("Found "+fList2.size()+" quakes that match filter criteria");
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }
    
    public void testMatchAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);   
        // for(QuakeEntry qe : list){ System.out.println(qe); }
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 2.0, "Magnitude"));
        maf.addFilter(new DepthFilter(-100000.0, -10000.0, "Depth"));
        //Location city = new Location(35.42, 139.43);
        //maf.addFilter(new DistanceFilter(10000000, city));
        maf.addFilter(new PhraseFilter("a", "any", "Phrase"));
        ArrayList<QuakeEntry> fList = filter(list, maf);
        for(QuakeEntry qe : fList){ System.out.println(qe); }
        System.out.println("read data for "+list.size()+" quakes");
        System.out.println("Found "+fList.size()+" quakes that match filter criteria"); 
        System.out.println(maf.getName());
    }
    
    public void testMatchAllFilter2(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);   
        // for(QuakeEntry qe : list){ System.out.println(qe); }
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 3.0, "Magnitude"));
        //maf.addFilter(new DepthFilter(-100000.0, -10000.0));
        Location Tulsa = new Location(36.1314, -95.9372);
        //Location Billund = new Location(55.7308, 9.1153);
        maf.addFilter(new DistanceFilter(10000000, Tulsa, "Distance"));
        maf.addFilter(new PhraseFilter("Ca", "any", "Phrase"));
        ArrayList<QuakeEntry> fList = filter(list, maf);
        for(QuakeEntry qe : fList){ System.out.println(qe); }
        System.out.println("read data for "+list.size()+" quakes");
        System.out.println("Found "+fList.size()+" quakes that match filter criteria");  
        System.out.println(maf.getName());
    }
}
