/**
 * Write a description of FindGene here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
//first ATG not after multiple of 3 from 0th index
//second ATG not after multiple of 3 away from 1st ATG and so on
public class Find_Gene {
    public String findGene (String dna){
        int numGene = 0, len60 = 0,maxLength = 0,cgRatio = 0;
        if(dna == ""){
            System.out.println("\nGene in DNA : ");
            System.out.println("\nNo of genes in DNA : " + numGene);
            return "";
        }
        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1){
            System.out.println("\nGene in DNA : ");
            System.out.println("\nNo of genes in DNA : " + numGene);
            return "";
        }
        while(true){
            int taaIndex = findStopIndex(dna,startIndex,"TAA");
            int tagIndex = findStopIndex(dna,startIndex,"TAG");
            int tgaIndex = findStopIndex(dna,startIndex,"TGA");
            int stopIndex = computeStopIndex(taaIndex,tagIndex,tgaIndex);
            if(stopIndex == -1)
                break;
            numGene++;
            String geneString = dna.substring(startIndex,stopIndex + 3);
            double cg = CGRatio(geneString);
            if(cg > 0.35)
                cgRatio++;
            System.out.println("\nGene in DNA : " + geneString);
            if(geneString.length() > maxLength)
                maxLength = geneString.length();
            if(geneString.length() > 60)
                len60++;
            startIndex = dna.indexOf("ATG",stopIndex + 3);
            if(startIndex == -1)
                break;
        }
        System.out.println("\nNo of genes in DNA : " + numGene);
        System.out.println("\nMax length gene : " + maxLength);
        System.out.println("\nno of genes with CG ratio greater than 0.35 : " + cgRatio);
        System.out.println("\nNo of genes in DNA with length more than 60 : " + len60);
        return "";
    }
    public int findStopIndex (String dna,int startIndex,String stopCodon){
        int stopIndex = dna.indexOf(stopCodon,startIndex + 3);
        if(stopIndex == -1)
            return -1;
        while(true){
            if((stopIndex - startIndex) % 3 == 0)
                break;
            stopIndex = dna.indexOf(stopCodon,stopIndex + 3);    
            if(stopIndex == -1)
                return -1;
        }
        return stopIndex; 
    }
    public int computeStopIndex (int taaIndex,int tagIndex,int tgaIndex){
        int minIndex = 0, stopIndex = 0;
        if(taaIndex == -1 || (tagIndex != -1 && tagIndex < taaIndex))
            minIndex = tagIndex;
        else
            minIndex = taaIndex;
        
        if(minIndex == -1 || (tgaIndex != -1 && tgaIndex < minIndex))
            stopIndex = tgaIndex;
        else
            stopIndex = minIndex;
        return stopIndex;      
    }
    public double CGRatio(String gene){
        double lencg = 0;
        int c,g;
        c = gene.indexOf('C');
        g = gene.indexOf('G');
        while(true){
            if(c!=-1){
                lencg++;
                c = gene.indexOf('C',c+1);
            }
            if(g!=-1){
                g = gene.indexOf('G',g+1);
                lencg++;
            }
            if(c==-1 && g==-1)
                break;
        }
        double genelen = gene.length();
        //System.out.println("\nCG Ratio : " + (lencg / dnalen));
        //System.out.println("\n" + lencg + "\n" + dnalen);
        return (lencg / genelen);
    }
    public void testFindGene(){
        //Write few test cases
        String dna = "ATGCTGTAGTAAATGTGA";
        //dna = dna.toUpperCase();
        System.out.println("Finding gene in DNA : " + dna);
        findGene(dna);
        int noCTG = 0,n = -1;
        while(true){
            n = dna.indexOf("CTG",n+1);
            if(n ==-1)
                break;
            noCTG++;    
        }
        System.out.println("\nCTG in dna : " + noCTG);
    }
}