
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource(filename);
         for(String line : fr.lines()) {
             records.add(WebLogParser.parseEntry(line));
         }
     }
     
     public HashMap<String,Integer> countVisitsPerIP() {
         HashMap<String,Integer> counts = new HashMap<String,Integer>();
         for(LogEntry le : records) {
             String ip = le.getIpAddress();
             if(counts.containsKey(ip))
                 counts.put(ip,counts.get(ip) + 1);
             else 
                 counts.put(ip,1);
         }
         return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String,Integer> count) {
         int maxVisit = 0;
         for(int counts : count.values()) {
             if(counts > maxVisit)
                 maxVisit = counts;
         }
         return maxVisit;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> count) {
         ArrayList<String> iPs = new ArrayList<String>();
         int maxVisit = mostNumberVisitsByIP(count);
         for(String iP : count.keySet())
             if(count.get(iP) == maxVisit)
                 iPs.add(iP);
         return iPs;
     }
     
     public HashMap<String,ArrayList<String>> iPsForDays() {
         HashMap<String,ArrayList<String>> iPsInDay = new HashMap<String,ArrayList<String>>();
         for(LogEntry le : records) {
             String ip = le.getIpAddress();
             String d = le.getAccessTime().toString().substring(4,10);
             if(!iPsInDay.containsKey(d))
                 iPsInDay.put(d,new ArrayList<String>());
             if(!iPsInDay.get(d).contains(ip))
                 iPsInDay.get(d).add(ip);
         }
         return iPsInDay;
     }
     
     public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> iPsInDay) {
         String maxDay = null;
         int maxIP = 0;
         for(String d : iPsInDay.keySet()) {
             if(maxIP < iPsInDay.get(d).size()) {
                 maxIP = iPsInDay.get(d).size();
                 maxDay = d;
             }
         }
         return maxDay;
     }
     
     
     //Check this method
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> iPsInDay,String day) {
         ArrayList<String> ip = new ArrayList<String>();
         ip.add("LOL");
         ArrayList<String> ipInDay = iPsInDay.get(day);
         HashMap<String,Integer> ipCounts = countVisitsPerIP();
         int maxVisit = 0;
         if(ipInDay != null) {
             for(String d : ipInDay) {
                 if(maxVisit < ipCounts.get(d)) {
                     maxVisit = ipCounts.get(d);
                 }
             }
             System.out.println("Max visiut no " + maxVisit);
             for(String d : ipInDay) {
                 System.out.println(d + "   " + ipCounts.get(d));            
                 if(maxVisit == ipCounts.get(d)) {
                     ip.add(d);
                    }
                }
         }
         
         return ip;
     }
     
     public void printAllHigherThanNum(int num) {
         System.out.println("IPs having status code higher than " + num + " are : ");
         for(LogEntry le : records) {
             if(le.getStatusCode() > num)
                 System.out.println(le);
         }
     }
     
     public int countUniqueIPs() {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for(LogEntry le : records) {
             String ipAddr = le.getIpAddress();
             if(!uniqueIPs.contains(ipAddr))
                 uniqueIPs.add(ipAddr);
         }
         return uniqueIPs.size();
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for(LogEntry le : records) {
             String ipAddr = le.getIpAddress();
             String d = le.getAccessTime().toString();
             //System.out.println(d);
             if(!uniqueIPs.contains(ipAddr) && d.indexOf(someday) != -1)
                 uniqueIPs.add(ipAddr);
         }
         return uniqueIPs;
     }
     
     public int countUniqueIPsInRange(int low,int high) {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for(LogEntry le : records) {
             String ipAddr = le.getIpAddress();
             if(!uniqueIPs.contains(ipAddr) && le.getStatusCode() >= low && le.getStatusCode() <= high)
                 uniqueIPs.add(ipAddr);
         }
         return uniqueIPs.size();
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
}
