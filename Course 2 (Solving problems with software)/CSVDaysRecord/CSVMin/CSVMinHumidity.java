/**
 * Write a description of CSVMinHumidity here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMinHumidity {
    public CSVRecord smallestOfTwo(CSVRecord currentRow,CSVRecord smallestSoFar){
        if(smallestSoFar == null)
                smallestSoFar = currentRow;
            else{
                String Humid = currentRow.get("Humidity");
                //System.out.println(Humid + currentRow.get("Humidity"));
                if(Humid.equals("N/A") == false){
                    //System.out.print("lolHumid" + Humid);
                    double currentHumid = Double.parseDouble(Humid);
                    //System.out.println("    " + currentHumid);
                    double smallestHumid = Double.parseDouble(smallestSoFar.get("Humidity"));
                    if(currentHumid < smallestHumid)
                        smallestSoFar = currentRow;
                }
            }
        return smallestSoFar;    
    }
    public CSVRecord lowestHumidityHourInFile(CSVParser parser){
        CSVRecord smallestSoFar = null;
        for(CSVRecord currentRow : parser){
            smallestSoFar = smallestOfTwo(currentRow,smallestSoFar);
        }
        return smallestSoFar;
    }
    public void testLowestHumidityDay(){
        FileResource fr = new FileResource();
        CSVRecord smallest = lowestHumidityHourInFile(fr.getCSVParser());
        System.out.println("Lowest Humidity was " + smallest.get("Humidity") + " at " + smallest.get("TimeEDT") + " lol " + smallest.get("DateUTC"));
    }
    public CSVRecord lowestHumidityInManyDays(){
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityHourInFile(fr.getCSVParser());
            smallestSoFar = smallestOfTwo(currentRow,smallestSoFar);
        }
        return smallestSoFar;
    }
    public void testLowestHumidityInManyDays(){
        CSVRecord smallest;
        smallest = lowestHumidityInManyDays();
        System.out.println("Lowest Humidity in many days was " + smallest.get("Humidity") + " at " + smallest.get("DateUTC"));
    }
}