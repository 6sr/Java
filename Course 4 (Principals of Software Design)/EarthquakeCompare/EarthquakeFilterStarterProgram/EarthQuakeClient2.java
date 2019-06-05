import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }
    
    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        
        MatchAllFilter maf = new MatchAllFilter();
        
        //Adding filters to the list
        maf.addFilter(new MagnitudeFilter(1.0,4.0));
        maf.addFilter(new DepthFilter(-180000.0,-30000.0));
        maf.addFilter(new PhraseFilter("any","o"));
        
        //Making list of qe satisfing f1,f2,f3
        ArrayList<QuakeEntry> temp = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : list) {
            if(maf.satisfies(qe)) {
                temp.add(qe);
            }
        }
        
        int no = 0;
        for(QuakeEntry qe : temp) {
            //System.out.println(qe);
            no++;
        }
        System.out.println("No of quakes filter 1 : " + no);
        System.out.println("Filters used are : " + maf.getName());
    }
    
    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        
        MatchAllFilter maf = new MatchAllFilter();
        
        //Adding filters to the list
        maf.addFilter(new MagnitudeFilter(0.0,5.0));
        maf.addFilter(new DistanceFilter(new Location(55.7308, 9.1153),3000000));
        maf.addFilter(new PhraseFilter("any","e"));
        
        //Making list of qe satisfing f1,f2,f3
        ArrayList<QuakeEntry> temp = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : list) {
            if(maf.satisfies(qe)) {
                temp.add(qe);
            }
        }
        
        int no = 0;
        for(QuakeEntry qe : temp) {
            //System.out.println(qe);
            no++;
        }
        System.out.println("No of quakes filter 2 : " + no);
        System.out.println("Filters used are : " + maf.getName());
    }
    

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            //System.out.println("LOL");
            if (f.satisfies(qe)) { 
                answer.add(qe);
            }
        }
        return answer;
    }

    public void quakesWithFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        
        Filter f1 = new MagnitudeFilter(3.5,4.5);
        Filter f2 = new DepthFilter(-55000.0,-20000.0);
        
        /*
        Location loc = new Location(39.7392, -104.9903);
        Filter f1 = new DistanceFilter(loc,1000000);
        Filter f2 = new PhraseFilter("end","a");
        */
        int no = 0;
        ArrayList<QuakeEntry> temp  = new ArrayList<QuakeEntry>(filter(list, f1));
        ArrayList<QuakeEntry> ans = new ArrayList<QuakeEntry>(filter(temp,f2));
        for (QuakeEntry qe: ans) {
            no++;
            System.out.println(qe);
        }
        System.out.println("No of quakes satisfying two criteria : " + no);
        
        /*
        for (QuakeEntry qe: list) {
            no++;
            System.out.println(qe);
        }
        System.out.println("No of quakes satisfying two criteria : " + no);
        */
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
}