
/**
 * Write a description of averageTemperatureWithHighHumidityInFile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class averageTemperatureWithHighHumidityInFile {
    public double avgTempInFile(CSVParser parser,int value){
        double avgTemp = 0;
        int numTemp = 0;
        for(CSVRecord currentRow : parser){
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            int currentHumidity = Integer.parseInt(currentRow.get("Humidity"));
            if(currentHumidity >= value){
                avgTemp += currentTemp;
                numTemp++;
            }
        }
        avgTemp /= numTemp;
        return avgTemp;
    }
    public void testAvgTempInDay(){
        FileResource fr = new FileResource();
        int value = 80;
        double average = avgTempInFile(fr.getCSVParser(),value);
        System.out.println("Average temperature of days having temperature greater than equal to " + value + " in file is " + average);
    }
}