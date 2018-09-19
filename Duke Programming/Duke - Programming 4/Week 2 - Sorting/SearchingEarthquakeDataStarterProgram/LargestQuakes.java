//2017 Christopher Mogush
//Largest Quakes

import java.util.*;
import java.util.Scanner;

public class LargestQuakes {
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        if(quakeData.size() < howMany){ howMany = quakeData.size(); }
        for(int i = 0; i < howMany; i++){
            int indexMagMax = indexOfLargest(quakeData);
            answer.add(quakeData.get(indexMagMax));
            quakeData.remove(indexMagMax);
        }
        //returns an ArrayList of type QuakeEntry of the top howMany largest magnitude 
        //earthquakes from quakeData. The quakes returned should be in the ArrayList in 
        //order by their magnitude, with the largest magnitude earthquake in index position 0
        return answer;
    }
    
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        /* for(QuakeEntry q : list){
            System.out.println(q);
        } */
        /* int indexMagMax = indexOfLargest(list);
        System.out.println("The quake with the highest magnitude occurs at index: " + indexMagMax);
        System.out.println(list.get(indexMagMax)); */
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter # of quakes to check: ");
        int howMany = scan.nextInt();
        ArrayList<QuakeEntry> filteredData = getLargest(list, howMany);
        System.out.println("Read data for "+list.size()+" quakes");
        System.out.println("The " + howMany + " quakes with the highest magnitude:");
        for(QuakeEntry q : filteredData){
        System.out.println(q);
        }
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> data){
        int answer = 0;
        double magMax = 0;
        for(QuakeEntry q : data){
            if(q.getMagnitude() > magMax){
                magMax = q.getMagnitude();
                answer = data.indexOf(q);
            }
        }
        //returns an integer representing the index location in data of the earthquake 
        //with the largest magnitude
        return answer;
    }
}
