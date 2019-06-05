
/**
 * Write a description of Baby_Names here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Baby_Names {
    //Calculating no of births
    public void noOfBirths(CSVParser parser){
        int noTotal = 0,noGirl = 0,noBoy = 0;
        for(CSVRecord currentRow : parser){
            String gender = currentRow.get(1);
            noTotal++;
            if(gender.equals("M"))
                noBoy++;
            else if(gender.equals("F"))
                noGirl++;
        }
        System.out.println("Total no : " + noTotal); 
        System.out.println("Boys no : " + noBoy);        
        System.out.println("Girls no : " + noGirl);
    }
    //Has name and gender and return rank
    public int getRank(FileResource fr,String name,String gender){
        int rank = 1;
        int boy = 0;
        int girl = 0;
        CSVRecord nameRecord = null;
        CSVParser parser = fr.getCSVParser(false);
        for(CSVRecord currentRow : parser){
            String currentName = currentRow.get(0);
            String currentGender = currentRow.get(1);
            if(name.equals(currentName) && gender.equals(currentGender)){
                nameRecord = currentRow;
                break;
            }
        }
        if(nameRecord == null)
            return -1;
        //if(!((nameRecord.get(1)).equals(gender)))
          //  return -2;
        //System.out.println("Name : " + nameRecord.get(0));
        int population = Integer.parseInt(nameRecord.get(2));
        parser = fr.getCSVParser(false);
        for(CSVRecord currentRow : parser){
            int currentPopulation = Integer.parseInt(currentRow.get(2));
            //System.out.println(" lol ");
            String currentGender = currentRow.get(1);
            if(population < currentPopulation){
                if(currentGender.equals(gender))
                    rank++;
                //System.out.println(currentGender);
                if(currentGender.equals("M"))
                    boy += currentPopulation; //System.out.println(currentGender.equals("M"));}
                if(currentGender.equals("F"))
                    girl += currentPopulation; //System.out.println(currentGender.equals("F"));}
            }
        }

        parser = fr.getCSVParser(false);
        for(CSVRecord currentRow : parser){
            int currentPopulation = Integer.parseInt(currentRow.get(2));
            //System.out.println(" lol ");
            if(currentRow == nameRecord)
                break;
            String currentGender = currentRow.get(1);
            if(population == currentPopulation){
                if(currentGender.equals(gender))
                    rank++;
                //System.out.println(currentGender);
                if(currentGender.equals("M"))
                    boy += currentPopulation; //System.out.println(currentGender.equals("M"));}
                if(currentGender.equals("F"))
                    girl += currentPopulation; //System.out.println(currentGender.equals("F"));}
            }
        }
        
        //System.out.println("Boy above : " + boy + "  Girl above : " + girl);
        return rank;
    }
    //Returns rank
    public int getRankName(FileResource fr,CSVRecord record){
        int rank = 1;
        int population = Integer.parseInt(record.get(2));
        String gender = record.get(1);
        CSVParser parser = fr.getCSVParser(false);
        for(CSVRecord currentRow : parser){
            String currentGender = currentRow.get(1);
            int currentPopulation = Integer.parseInt(currentRow.get(2));
            //System.out.println(" lol ");
            if(population < currentPopulation && currentGender.equals(gender))
                rank++;
        }

        parser = fr.getCSVParser(false);
        for(CSVRecord currentRow : parser){
            if(currentRow == record)
                break;
            String currentGender = currentRow.get(1);
            int currentPopulation = Integer.parseInt(currentRow.get(2));
            //System.out.println(" lol ");
            if(population == currentPopulation && currentGender.equals(gender))
                rank++;
        }
        
        //System.out.println(name + " Rank : " + rank);
        return rank;
    }
    //Name of a rank
    public String getName(FileResource fr,String gender,int rank){
        String name = null;
        CSVParser parser = fr.getCSVParser(false);     
        int currentRank = 0;
        //int population = 0;
        for(CSVRecord currentRow : parser){
            //int currentNamePopulation = Integer.parseInt(currentRow.get(2));
            //System.out.println(" LOL1 " + currentRank);
            String currentGender = currentRow.get(1);
            if(currentGender.equals(gender)){
                currentRank = getRankName(fr,currentRow);
                //System.out.println(" LOL2 " + currentRank);
                if(currentRank == rank) {
                    name = currentRow.get(0);
                    System.out.println("Name found gender : " + currentRow.get(1));
                    break;
                }
            }
        }
        return name;
    }
    public void tester(){
        DirectoryResource dr = new DirectoryResource();
        int year = 1879,largestRank = 0,largestRankYear = 0;
        double avgRank = 0,avgNo = 0;
        
        for(File f : dr.selectedFiles()){
            year++;
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            
            //noOfBirths(parser);
            
            //int rank = getRank(fr,"Owen","M");
            //System.out.println("Name has Rank : " + rank);
            for(CSVRecord rec : parser){
                String name = rec.get(0);
                String gender = rec.get(1);
                int rank = getRankName(fr,rec);
                System.out.println(name + "  " + gender + "  " + rec.get(2) + "  Rank : " + rank);
            }
            //if(largestRank < rank){
              //  largestRank = rank;
                //largestRankYear = year;
                //if(largestRank == 693)
                  //  System.out.println("Yearrrrrrrrrrrr : " + year);
            //}
            
            //String name = getName(fr,"M",432);
            //System.out.println("Rank has Name : " + name);
            //if(rank > 0) {
              //  avgNo++;
              //  avgRank += rank;
            //}
        }
        //avgRank /= year;
        //System.out.println("Avg Rank : " + avgRank);
        //System.out.println("Largest Rank : " + largestRank + "Largest Rank Year : " + largestRankYear);
    }
}
