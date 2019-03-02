
/**
 * Write a description of part_1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class part_1 {
    public String findSimpleGene(String dna){
        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1)
            return "";
        int stopIndex = dna.indexOf("TAA",startIndex);
        if(stopIndex == -1)
            return "";
        if((stopIndex - startIndex) % 3 == 0)
            return dna.substring(startIndex,stopIndex + 3);
        return "";    
    }
    public void testSimpleGene(){
        //Write few test cases
        String dna = "";
        String gene = findSimpleGene(dna);
        System.out.println("Gene in DNA = " + gene);
    }
}
