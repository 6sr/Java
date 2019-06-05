/**
 * Write a description of CSVMin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMinTemp {
    public CSVRecord smallestOfTwo(CSVRecord currentRow,CSVRecord smallestSoFar){
        if(smallestSoFar == null)
                smallestSoFar = currentRow;
            else{
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
                if(currentTemp < smallestTemp)
                    if(currentTemp != -9999)
                        smallestSoFar = currentRow;
            }
        return smallestSoFar;
    }
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord smallestSoFar = null;
        for(CSVRecord currentRow : parser){
            smallestSoFar = smallestOfTwo(currentRow,smallestSoFar);
        }
        return smallestSoFar;
    }
    public void testcoldestDay(){
        FileResource fr = new FileResource();
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + smallest.get("TemperatureF") + " at " + smallest.get("TimeEDT"));
    }
    public CSVRecord coldestInManyDays(){
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord current = coldestHourInFile(fr.getCSVParser());
            smallestSoFar = smallestOfTwo(current,smallestSoFar);
        }
        return smallestSoFar;
    }
    public void testcoldestInManyDays(){
        CSVRecord smallest = coldestInManyDays();
        System.out.println("coldest temperature in many days was " + smallest.get("TemperatureF") + " at " + smallest.get("DateUTC"));
    }
}
