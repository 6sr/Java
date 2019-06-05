
/**
 * Write a description of part_2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

//input lowercase then output gene lower
//input uppercase then output gene upper

import edu.duke.*;

public class part_2 {
    public String findSimpleGene(String dna,String startCodon,String stopCodon){
        int startIndex = dna.indexOf(startCodon);
        if(startIndex == -1)
            return "";
        int stopIndex = dna.indexOf(stopCodon,startIndex);
        if(stopIndex == -1)
            return "";
        if((stopIndex - startIndex) % 3 == 0)
            return dna.substring(startIndex,stopIndex + 3);
        return "";    
    }
    public void testSimpleGene(){
        //Write few test cases
        String dna = "";
        String gene = findSimpleGene(dna,"ATG","TAA");
        System.out.println("Gene in DNA = " + gene);
    }
}
