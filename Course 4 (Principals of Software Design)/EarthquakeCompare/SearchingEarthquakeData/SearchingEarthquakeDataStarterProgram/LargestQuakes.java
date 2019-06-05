
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class LargestQuakes {
    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        int maxIdx = 0;
        double max = 0.0;
        int i = 0;
        for(QuakeEntry qe : data) {
            if(max < qe.getMagnitude()) {
                max = qe.getMagnitude();
                maxIdx = i;
            }
            i++;
        }
        return maxIdx;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData,int howMany) {
        if(howMany > quakeData.size()) {
            return quakeData;
        }
        
        ArrayList<QuakeEntry> ans = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        
        for(int i = 0;i < howMany;i++) {
            int idx = indexOfLargest(copy);
            QuakeEntry curr = copy.get(idx);
            ans.add(curr);
            copy.remove(idx);
        }
        return ans;
    }
    
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        
        ArrayList<QuakeEntry> largest = getLargest(list,50);
        for(QuakeEntry qe : largest) {
            System.out.println(qe);
        }
        
        /*
        int no = 0;
        for(QuakeEntry qe : list) {
            System.out.println(qe);
            no++;
        }
        System.out.println("No of quakes : " + no);
        */
    }
}