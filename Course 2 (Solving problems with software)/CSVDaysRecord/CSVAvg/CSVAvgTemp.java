
/**
 * Write a description of CSVAvgTemp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVAvgTemp {
    public double avgTempInFile(CSVParser parser){
        double avgTemp = 0;
        int numTemp = 0;
        for(CSVRecord currentRow : parser){
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            avgTemp += currentTemp;
            numTemp++;
        }
        avgTemp /= numTemp;
        return avgTemp;
    }
    public void testAvgTempInDay(){
        FileResource fr = new FileResource();
        double average = avgTempInFile(fr.getCSVParser());
        System.out.println("Average temperature in file is " + average);
    }
}
