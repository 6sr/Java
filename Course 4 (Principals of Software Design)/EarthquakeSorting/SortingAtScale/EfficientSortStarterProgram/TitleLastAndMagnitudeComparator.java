
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String lastWordq1 = q1.getInfo().substring(q1.getInfo().lastIndexOf(" ") + 1);
        String lastWordq2 = q2.getInfo().substring(q2.getInfo().lastIndexOf(" ") + 1);
        if(lastWordq1.equals(lastWordq2)) {
            return Double.compare(q1.getMagnitude(),q2.getMagnitude());
        }
        return lastWordq1.compareTo(lastWordq2);
    }
}
