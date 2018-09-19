//2017 Christopher Mogush
//QuakeSortInPlace

import java.util.*;

public class QuakeSortInPlace {
    public int countPasses;
    
    public QuakeSortInPlace(){
        countPasses = 0;
    }
    
    public ArrayList<QuakeEntry> sortByMagnitude(ArrayList<QuakeEntry> in){
        //count from 0 to < in.size()
        for(int i = 0; i < in.size(); i++){   
            //find index of smallest quake
            int minIndex = getSmallestMagnitude(in, i);
            //swap it with the nth quake with minInxth quake
            QuakeEntry qi = in.get(i); //placeholder
            QuakeEntry qmin = in.get(minIndex);
            in.set(i, qmin);
            in.set(minIndex, qi);
        }
        return in;
    }
    
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from){
        int minIndex = from;
        for(int i = from + 1; i < quakes.size(); i++){
            if(quakes.get(i).getMagnitude() < quakes.get(minIndex).getMagnitude()){
                minIndex = i;
            }
        }
        //returns the index with the smallest Magnitude
        return minIndex;
    }
    
    public ArrayList<QuakeEntry> sortByLargestDepth(ArrayList<QuakeEntry> in){
        //count from 0 to < in.size()
        for(int i = 0; i < 50; i++){   
            //find index of smallest quake
            int minIndex = getLargestDepth(in, i); //gets the largest depth index and sets it to minIndex
            //swap it with the nth quake with minInxth quake
            QuakeEntry qi = in.get(i); //holds current quake entry
            QuakeEntry qmin = in.get(minIndex); //gets quake entry for minIndex
            in.set(i, qmin); //set the minimum quake entry to the current index
            in.set(minIndex, qi); //set the minimum quake index to the current quake entry
        }
        return in;
    }
        public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from){
        int minIndex = from;
        for(int i = from + 1; i < quakes.size(); i++){
            if(quakes.get(i).getDepth() > quakes.get(minIndex).getDepth()){
                minIndex = i;
            }
        }
        //returns an integer representing the index position of the QuakeEntry with the largest depth 
        //considering only those QuakeEntry’s from position from to the end of the ArrayList
        return minIndex;
    }
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted){
        //numSorted that represents the number of times this method has already been called on 
        //this ArrayList and thus also represents the number of the elements that are guaranteed 
        //to already be where they belong
        //This method makes one pass of bubble sort on the ArrayList
        //should take advantage of the fact that the last numSorted elements are already in sorted order
        
        //BUBBLE SORT
        for(int i = 1; i < quakeData.size() - numSorted; i++){
            QuakeEntry qi1 = quakeData.get(i - 1);
            QuakeEntry qi2 = quakeData.get(i);
            if(qi1.getMagnitude() > qi2.getMagnitude()){
                quakeData.set(i - 1, qi2);
                quakeData.set(i, qi1);
            }
        }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
        //If the ArrayList in has N elements in it, this method should call 
        //onePassBubbleSort N – 1 times to sort the elements in in.
        for(int i = 0; i < in.size() - 1; i++){
            onePassBubbleSort(in, i);
            countPasses +=1 ;
        }
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes){
        //returns true if the earthquakes are in sorted order by magnitude from smallest to largest
        //otherwise returns false
        QuakeEntry current = quakes.get(0);
        for(int i = 1; i < quakes.size(); i++){
            if(current.getMagnitude() > quakes.get(i).getMagnitude()){
                return false;
            }
            current = quakes.get(i);
        }
        return true;
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){
        for(int i = 0; i < in.size() - 1; i++){
            onePassBubbleSort(in, i);
            if(checkInSortedOrder(in)){
                countPasses = i + 1;
                break;
            }
        }        
    }
    
    public ArrayList<QuakeEntry> sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
    //sorts earthquakes by their magnitude from smallest to largest using selection sort similar to the sortByMagnitude
    //should call checkInSortedOrder and stop early if the ArrayList is already sorted
    //should print how many passes were needed to sort the elements
    //for selection sort, 1 pass = 1 swap
        for(int i = 0; i < in.size(); i++){   
            //find index of smallest quake
            int minIndex = getSmallestMagnitude(in, i);
            //swap it with the nth quake with minInxth quake
            QuakeEntry qi = in.get(i); //placeholder
            QuakeEntry qmin = in.get(minIndex);
            in.set(i, qmin);
            in.set(minIndex, qi);
            if(checkInSortedOrder(in)){
                countPasses = i + 1;
                return in;
            }
        }
        return in;
    }
    
    public void testSort(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("Original list:");
        for(QuakeEntry qe : list){
            System.out.println(qe);
        }
        //list = sortByMagnitude(list);
        //list = sortByLargestDepth(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        System.out.println("Final list:");
        for(QuakeEntry qe : list){
            System.out.println(qe);
        }
        System.out.println("read data for "+list.size()+" quakes");
        System.out.println("Sort finished after " + countPasses + " passes");
    }
}
