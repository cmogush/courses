//2017 Christopher Mogush
//Tester
//linked to LogEntry and LogAnalyzer

import edu.duke.*;
import java.util.*;
public class Tester {
    public void testLogEntry(){
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer(){
        LogAnalyzer la = new LogAnalyzer(); //creates new LogAnalyzer object
        la.readFile("test/short-test_log.txt");
        la.printAll();
    }
    
    public void testUniqueIPs(){
        LogAnalyzer la = new LogAnalyzer(); //creates new LogAnalyzer object
        la.readFile("test/weblog2_log.txt");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are " + uniqueIPs + " unique IP addresses.");
    }
    
    public void testUniqueIPVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer(); //creates new LogAnalyzer object
        la.readFile("test/weblog2_log.txt");
        String day = "Sep 27";
        ArrayList<String> uniqueVisits = new ArrayList<String>(la.uniqueIPVisitsOnDay(day));
        System.out.println("The unique IPs visiting on " + day + " are:");
        for(String s : uniqueVisits){
            System.out.println(s);
        }
        System.out.println("Total #: " + uniqueVisits.size());
    }
    
    public void testCountUniqueIPsInRange(){
        LogAnalyzer la = new LogAnalyzer(); //creates new LogAnalyzer object
        la.readFile("test/weblog2_log.txt");
        int low = 200;
        int high = 299;
        System.out.println("The # of unique IPs in the range of " + low + " and " + high 
        + " is: " + la.countUniqueIPsInRange(low, high));
    }
    
    public void testPrintHigherThanNum(){
        LogAnalyzer la = new LogAnalyzer(); //creates new LogAnalyzer object
        la.readFile("test/weblog1_log.txt");
        int num = 400;
        la.printAllHigherThanNum(num);
    }
    
    public void testCounts(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("test/weblog2_log.txt");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
        System.out.println("Highest # of visits for an ip: " + la.mostNumberVisitsByIP(counts));
        System.out.println("IPs with max # of visits: " + la.iPsMostVisits(counts));
    }
    
    public void testIPsForDays(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("test/weblog3-short_log.txt");
        System.out.println(la.iPsForDays());
    }
    
    public void testDayWithMostIPVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("test/weblog2_log.txt");
        HashMap<String, ArrayList<String>> map = la.iPsForDays();
        System.out.println(la.dayWithMostIPVisits(map));
    }
    
    public void testIPsWithMostVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("test/weblog2_log.txt");
        HashMap<String, ArrayList<String>> map = la.iPsForDays();
        String day = "Sep 29";
        System.out.println(la.iPsWithMostVisitsOnDay(map, day));
    }
}
