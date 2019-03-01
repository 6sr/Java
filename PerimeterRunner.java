 /**
 * Write a description of PerimeterRunner here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class PerimeterRunner {
    public double getPerimeter (Shape s){       //Returns perimeter of Shape s
        double totalPerim = 0;
        Point prevPt = s.getLastPoint();
        for(Point currPt: s.getPoints()){
            double currDist = prevPt.distance(currPt);
            totalPerim = totalPerim + currDist;
            prevPt = currPt;
        }
        return totalPerim;
    }
    public double getLargestPerimeterMultipleFiles (){      //Returns largest perimeter among multiple files
        DirectoryResource dr = new DirectoryResource();
        double LargestPerim = 0;
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double totalPerim = getPerimeter(s);
            if(totalPerim > LargestPerim)
                LargestPerim = totalPerim;
        }
        return LargestPerim;
    }
    public String getFileWithLargestPerimeter (){       //Returns FileName having shape with largest perimeter
        DirectoryResource dr = new DirectoryResource();
        double LargestPerim = 0;
        String FileName = "";
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double totalPerim = getPerimeter(s);
            if(totalPerim > LargestPerim){
                LargestPerim = totalPerim;
                FileName = f.getName();
            }
        }
        return FileName;
    }
    public int getNumPoints(Shape s){           //Returns number of points in Shape s
        int NumPts = 0;
        for(Point currPt: s.getPoints()) {
            NumPts++;
        }
        return NumPts;
    }
    public double getAverageLength(Shape s){     //Returns average of side lengths of Shape s
        double totalPerim = getPerimeter(s);
        int NumPts = getNumPoints(s);
        double AvgLength = totalPerim / NumPts;
        return AvgLength;
    }
    public double getLargestSide(Shape s){      //Returns length of largest side
        double LargestSide = 0;
        Point prevPt = s.getLastPoint();
        for(Point currPt: s.getPoints()){
            double currDist = prevPt.distance(currPt);
            if(currDist > LargestSide)
                LargestSide = currDist;
            prevPt = currPt;    
        }
        return LargestSide;
    }
    public double getLargestX(Shape s){     //Returns point with largest x-coordinate
        Point pt = s.getLastPoint();
        double LargestX = pt.getX();
        for(Point currPt: s.getPoints()){
            if(currPt.getX() > LargestX)
                LargestX = currPt.getX();
        }
        return LargestX;        
    }
    public void testPerimeterMultipleFiles (){      //Calls method to calculate largest perimeter among multiple files
        double LargestPerim = getLargestPerimeterMultipleFiles();
        System.out.println("Largest Perimeter = " + LargestPerim);
    }
    public void testFileWithLargestPerimeter (){      //Calls method to know name of file with largest perimeter among multiple files
        String FileName = getFileWithLargestPerimeter();
        System.out.println("File with largest perimeter = " + FileName);
    }
    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        
        int NumPoints = getNumPoints(s);
        System.out.println("No of Points = " + NumPoints);
        
        double AvgLength = getAverageLength(s);
        System.out.println("Average of all side lengths = " + AvgLength);
        
        double LargestSide = getLargestSide(s);
        System.out.println("Length of largest side = " + LargestSide);
        
        double LargestX = getLargestX(s);
        System.out.println("Largest x coordinate = " + LargestX);
        
        double length = getPerimeter(s);
        System.out.println("Perimeter = " + length);
    }
    public static void main (String[] args){
        PerimeterRunner pr = new PerimeterRunner();
        pr.testPerimeterMultipleFiles();
    }
}