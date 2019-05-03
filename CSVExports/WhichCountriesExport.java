
/**
 * Write a description of WhichCountriesExport here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {

    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        String country = countryInfo(parser,"Nauru");
        System.out.println("Info : " + country);
        
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser,"cotton","flowers");
        
        parser = fr.getCSVParser();
        int num = numberOfExporters(parser,"cocoa");
        System.out.println("Num sugar :" + num);
        
        parser = fr.getCSVParser();
        bigExporters(parser,"$999,999,999,999");
    }
    public String countryInfo(CSVParser parser,String country){
        String countryData;
        for(CSVRecord record: parser){
            String Country = record.get("Country");
            if(Country.contains(country)){
                countryData = record.get("Country") + ":" + record.get("Exports") + ":" + record.get("Value (dollars)");
                return countryData;
            }
        }
        return "NOT FOUND";
    }
    public void listExportersTwoProducts(CSVParser parser,String exportItem1,String exportItem2){
            for(CSVRecord record: parser){
            String export = record.get("Exports");
            if(export.contains(exportItem1))
                if(export.contains(exportItem2))
                    System.out.println(exportItem1 + exportItem2 + " sell : " + record.get("Country"));
        }        
    }
    public int numberOfExporters(CSVParser parser,String exportItem){
        int numExporters = 0;
        for(CSVRecord record: parser){
            String export = record.get("Exports");
            if(export.contains(exportItem))
                numExporters++;
        }
        return numExporters;
    }
    public void bigExporters(CSVParser parser,String amount){
        for(CSVRecord record: parser){
            String value = record.get("Value (dollars)");
            int l = value.length();
            //System.out.println(l);
            if(l > 16){
                System.out.print(record.get("Country"));
                System.out.println(value);
            }
        }
    }
}
