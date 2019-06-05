
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    //Selection Sort
    //By Depth
    public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from) {
        int maxIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getDepth() > quakes.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }
    
    public void sortByDepth(ArrayList<QuakeEntry> in) {
       for (int i=0; i< in.size(); i++) {
            int maxIdx = getLargestDepth(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(i,qmax);
            in.set(maxIdx,qi);
            //Breaks in 50th pass
            if(i == 49)
                break;
        }
    }

    //By Magnitude
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
       }    
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
       int i = 0;
       for (;i < in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            if(checkInSortedOrder(in)) {
                break;
            }
       }
       System.out.println("No of passes with Selection sort : " + (i + 1));
    }

    //Bubble Sort
    void onePassBubbleSort(ArrayList<QuakeEntry> in,int numSorted) {
        for (int i = 0; i< in.size() - numSorted - 1; i++) {
            QuakeEntry qCurr = in.get(i);
            QuakeEntry qNext = in.get(i + 1);
            if(qCurr.getMagnitude() > qNext.getMagnitude()) {
                in.set(i,qNext);
                in.set(i + 1,qCurr);
               }
        }
    }
    
    void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        for(int i = 0;i < in.size() - 1;i++) {
            onePassBubbleSort(in,i);
        }
    }
    
    void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        int i = 0;
        for(;i < in.size() - 1;i++) {
            onePassBubbleSort(in,i);
            if(checkInSortedOrder(in)) {
                break;
            }
        }
        System.out.println("No of passes with Bubble sort : " + (i + 1));
    }
    
    boolean checkInSortedOrder(ArrayList<QuakeEntry> in) {
        for(int i = 0;i < in.size() - 1;i++) {
            QuakeEntry qCurr = in.get(i);
            QuakeEntry qNext = in.get(i + 1);
            if(qCurr.getMagnitude() > qNext.getMagnitude()) {
                return false;
            }
        }
        return true;
    }
    
    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByDepth(list);
        //sortByMagnitudeWithBubbleSort(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        //sortByMagnitude(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        }
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
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
}