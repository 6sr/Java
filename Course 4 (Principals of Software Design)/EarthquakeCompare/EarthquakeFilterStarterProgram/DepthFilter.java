
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter {
    private double depthMin; 
    private double depthMax;
    
    public DepthFilter(double min,double max) { 
        depthMin = min;
        depthMax = max;
    }

    public boolean satisfies(QuakeEntry qe) {
        double curr = qe.getDepth();
        return (curr >= depthMin && curr <= depthMax);
    }
    
    public String getName() {
        return "Depth";
    }
}
