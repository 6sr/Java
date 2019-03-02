
/**
 * Write a description of StorageResource here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
//import java.lang.Object;

public class StorageResource {
    public StorageResource findSimpleGene(String dna){
        StorageResource sr = new StorageResource();
        while(true){
            int startIndex = dna.indexOf("ATG");
            if(startIndex == -1)
                break;
            int stopIndex = dna.indexOf("TAA",startIndex);
            if(stopIndex == -1)
                break;
            if((stopIndex - startIndex) % 3 == 0){
                String gene = dna.substring(startIndex,stopIndex + 3);
                //System.out.println(gene);
                sr.add(gene);
            }
        }
        return sr;
    }
    public void testSimpleGene(){
        //Write few test cases
        String dna = "TGAATGTACCAGTGCTAAAGCATGTGCTAAGTC";
        StorageResource sr = new StorageResource();
        sr = findSimpleGene(dna);
        for(String item: sr.data())
            System.out.println("\nGene in dna : " + item);
        //System.out.println("\nGene in DNA = " + gene);
    }
}