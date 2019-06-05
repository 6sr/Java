
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog2_log");
        
        log.printAll();
        
        System.out.println("Unique IPs : " + log.countUniqueIPs());
        
        log.printAllHigherThanNum(400);
        
        ArrayList<String> uniqueIPsOnDay = log.uniqueIPVisitsOnDay("Sep 24");
        System.out.println("Unique IPs on day : ");
        for(int i = 0;i < uniqueIPsOnDay.size();i++)
            System.out.println(uniqueIPsOnDay.get(i));
        System.out.println("Unique IPS on day : " + uniqueIPsOnDay.size());
        
        System.out.println("Unique IPs in range : " + log.countUniqueIPsInRange(400,499));   
        
        HashMap<String,Integer> counts = log.countVisitsPerIP();
        System.out.println("Counts per IP visit : " + counts);
        
        System.out.println("Most no visit by IP : " + log.mostNumberVisitsByIP(counts));
        
        System.out.println("Most IP visited : " + log.iPsMostVisits(counts));
        
        HashMap<String,ArrayList<String>> iPsInDay = log.iPsForDays();
        
        System.out.println("Day with most IP visits : " + log.dayWithMostIPVisits(iPsInDay));
        
        System.out.println("Max visit by which IP address on day : " + log.iPsWithMostVisitsOnDay(iPsInDay,"Sep 30"));        
    }
}