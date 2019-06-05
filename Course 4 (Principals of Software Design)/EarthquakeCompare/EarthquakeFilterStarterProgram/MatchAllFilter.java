
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MatchAllFilter implements Filter {
    private ArrayList<Filter> filter;
    
    public MatchAllFilter() {
        filter = new ArrayList<Filter>();
    }
    
    public void addFilter(Filter f) {
        filter.add(f);
    }
    
    public boolean satisfies(QuakeEntry qe) {
        for(Filter f : filter) {
            if(!f.satisfies(qe)) {
                return false;
            }
        }
        return true;
    }
    
    public String getName() {
        String names = new String(" ");
        for(Filter f : filter) {
            names = names + " " + f.getName();
        }
        return names;
    }
}