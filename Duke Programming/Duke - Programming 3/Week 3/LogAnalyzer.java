//2017 Christopher Mogush
//LogAnalyzer

import java.util.*;
import edu.duke.*;
public class LogAnalyzer {
    private ArrayList<LogEntry> records; //LogEntry is the class we made! :D
    private ArrayList<String> uniqueIPs; //ArrayList for unique LogEntries
    private ArrayList<Integer> uniqueStatusCodes;
    private HashMap<String, Integer> counts;
    
    public LogAnalyzer(){ //constructor
        //initialize "records" to an empty LogEntry ArrayList
        records = new ArrayList<LogEntry>();
        uniqueIPs = new ArrayList<String>();
        uniqueStatusCodes = new ArrayList<Integer>();
        counts = new HashMap<String, Integer>();
        
        records.clear();
        uniqueIPs.clear();
        uniqueStatusCodes.clear();
        counts.clear();
    }
    
    public void readFile(String filename){
        //determine the filename to read from
        FileResource fr = new FileResource(filename);
        //add log entries to "records" field to reflect the information from the file opened
        //create FileResource and Iterate, using WebLogParser.parseEntry method
        for(String line : fr.lines()){
            //Add entry to "records" field
            records.add(WebLogParser.parseEntry(line));
        }
    }
    
    public void printAll(){
        for(LogEntry le : records){
            System.out.println(le); //implicitly uses .toString() in LogEntry
        }
    }
    
    public int countUniqueIPs(){
        for(LogEntry le : records){
            if(!uniqueIPs.contains(le.getIpAddress())){
                uniqueIPs.add(le.getIpAddress());
            }
        }
        return uniqueIPs.size(); //valid int
    }
    
    public ArrayList<String> uniqueIPVisitsOnDay(String someday){ //MMM DD, e.g. Dec 05, Apr 22
        for(LogEntry le : records){
            if((le.getAccessTime().toString()).contains(someday)){
                if(!uniqueIPs.contains(le.getIpAddress())){
                    uniqueIPs.add(le.getIpAddress());
                }
            }
        }
        return uniqueIPs; //valid int
    }
    
    public int countUniqueIPsInRange(int low, int high){
        for(LogEntry le : records){
            if(le.getStatusCode() >= low && le.getStatusCode() <= high){
                if(!uniqueIPs.contains(le.getIpAddress())){
                       uniqueIPs.add(le.getIpAddress());
                }
            }
        }
        return uniqueIPs.size(); //valid int
        //returns # of unique IP in records with status code in range, inclusive.
    }
    
    public void printAllHigherThanNum(int num){
        System.out.println("All codes in records higher than " + num + ":");
        for(LogEntry le : records){
            if(le.getStatusCode() > num){
                if(!uniqueStatusCodes.contains(le.getStatusCode())){
                       uniqueStatusCodes.add(le.getStatusCode());
                }
                System.out.println(le.getStatusCode());
            }
        }
    }
    
    public HashMap<String, Integer> countVisitsPerIP(){
        for(LogEntry le : records){
            String ip = le.getIpAddress();
            if(!counts.containsKey(le.getIpAddress())){
                counts.put(ip, 1);
            }
            else {
                counts.put(ip, counts.get(ip) + 1);
            }
        }
        return counts; 
    }
    
    public int mostNumberVisitsByIP(HashMap<String, Integer> map){
        int max = 0;
        for(Integer v : map.values()){
            if(v > max){
                max = v;
            }
        }
        //returns max num of visits to this website by a single IP address
        return max;
    }
    
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> map){
        //map is an IP address to the # of times it appears in the web log file
        ArrayList<String> list = new ArrayList<String>();
        int max = mostNumberVisitsByIP(map);
        for(String s : map.keySet()){
            if(map.get(s) == max){
                list.add(s);
            }
        }
        //returns ArrayList of Strings of IP address that all have the max #
        return list;
    }
    
    public HashMap<String, ArrayList<String>> iPsForDays(){
        //returns hashmap that uses 'records' and maps days from web logs
        //to an ArrayList of IPs that occured on that day (include repeats)
        //format is "MMM DD" e.g. "Apr 22"
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        for(LogEntry le : records){ //if ip address occurs on day, add to arraylist
            //first check the day and ip address
            String accessTime = le.getAccessTime().toString(); 
            String day = accessTime.substring(4, 10);
            String ip = le.getIpAddress();
            if(!map.containsKey(day)){ //if day is not yet stored as key
               map.put(day, new ArrayList<String>()); //add the key and create a new arraylist
               map.get(day).add(ip);
            }
            else{
               map.get(day).add(ip); //add ip to arraylist for key
            }
        }
        return map;
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map){
        String day = null;
        int most = 0;
        for(String s : map.keySet()){
            if(map.get(s).size() > most){
                day = s;
                most = map.get(s).size();
            }
        }
        //return day with most IP visits
        return day;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String day){
        //returns an ArrayList<String> of IPs that had the most accesses on day
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        //make "counts" a map for IP address to the # of times it appears in on day
        for(String s : map.get(day)){ //iterate over arraylist for the day
            if(!counts.containsKey(s)){
                counts.put(s, 1);
            }
            else {
                counts.put(s, counts.get(s) + 1);
            }
        }
        return iPsMostVisits(counts);
    }
}
