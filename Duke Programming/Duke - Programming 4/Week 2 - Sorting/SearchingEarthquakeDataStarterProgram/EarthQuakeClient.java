import java.util.*;
import edu.duke.*;
import java.util.Scanner;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }
    
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry q : quakeData){
            if(q.getMagnitude() > magMin){
                answer.add(q);
            }
        }
        //return an ArrayList of type QuakeEntry of all the earthquakes from 
        //quakeData that have a magnitude larger than magMin
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry q : quakeData){
            if(from.distanceTo(q.getLocation()) / 1000 < distMax){
                answer.add(q);
            }
        }
        //return an ArrayList of type QuakeEntry of all the earthquakes from
        //quakeData that are less than distMax from the location from
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, 
    double minDepth, double maxDepth){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry q : quakeData){
            if(q.getDepth() < minDepth && q.getDepth() > maxDepth){
                answer.add(q);
            }
        }
        return answer;
        //returns an arraylist of type quakeentry of all earthquakes BETWEEN minDepth and maxDepth (exclusive)
    }    
        
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
    String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry q : quakeData){
            if(where.equals("start")){
                if(q.getInfo().startsWith(phrase)){ answer.add(q);}
            }
            if(where.equals("any")){
                if(q.getInfo().indexOf(phrase) > -1) { answer.add(q);}
            }
            if(where.equals("end")){
                if(q.getInfo().endsWith(phrase)){ answer.add(q);}
            }
        }
        //return ArrayList of type QuakeEntry of all the earthquakes from quakeData 
        //whose titles have the given phrase found at location where
        return answer;
    }
    
    ////////////////////////////
    
    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        double magMin = 5.0;
        ArrayList<QuakeEntry> filteredData = filterByMagnitude(list, magMin);
        for(QuakeEntry q : filteredData){
            System.out.println(q);
        }
        System.out.println("read data for "+list.size()+" quakes");
        System.out.println("Found " + filteredData.size() + " quakes with a minimum magnitude of: " + magMin);
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);
        double distMax = 1000;
        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);
        ArrayList<QuakeEntry> filteredData = filterByDistanceFrom(list, distMax, city);
        for(QuakeEntry q : filteredData){
            System.out.println(city.distanceTo(q.getLocation())/1000 + " " + q.getInfo());
        }
        System.out.println("Read data for "+list.size()+" quakes");
        System.out.println("Found " + filteredData.size() + " quakes within a distance of : " + distMax + " km");
    }

    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter max depth to check: ");
        double maxDepth = scan.nextDouble();
        System.out.println("Enter min depth to check: ");
        double minDepth = scan.nextDouble();
        ArrayList<QuakeEntry> filteredData = filterByDepth(list, minDepth, maxDepth);
        for(QuakeEntry q : filteredData){
            System.out.println(q);
        }
        System.out.println("Read data for "+list.size()+" quakes");
        System.out.println("Found " + filteredData.size() + " quakes with a depth between: " + minDepth + " and " + maxDepth);
    }
    
    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter phrase to check: ");
        String phrase = scan.nextLine();
        System.out.println("Enter phrase location (start/any/end):");
        String where = scan.nextLine();
        ArrayList<QuakeEntry> filteredData = filterByPhrase(list, where, phrase);
        for(QuakeEntry q : filteredData){
            System.out.println(q);
        }
        System.out.println("Read data for "+list.size()+" quakes");
        System.out.println("Found " + filteredData.size() + " quakes with a the phase " + phrase + " at " + where + " location.");
        //print all the earthquakes from a data source that have phrase in their title in a 
        //given position in the title. Print out the number of earthquakes found
    }
    
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
}
