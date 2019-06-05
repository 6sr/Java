
/**
 * Write a description of codon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class codon {
    //private HashMap<Integer,String> frame;
    private HashMap<String,Integer> count;
    private int unique;
    public codon() {
        unique = 0;
        //frame = new HashMap<Integer,String>();
        count = new HashMap<String,Integer>();
    }
    private void buildCodon(int start,String dna) {
        count.clear();
        String current = dna.substring(start);// + dna.substring(0,start);
        StringBuilder curr = new StringBuilder(current);
        for(int i = 0;i < current.length() - 2;i = i + 3) {
            if(!Character.isLetter(curr.charAt(i + 2)))
                break;
            String codon = current.substring(i,i + 3);
            if(count.containsKey(codon))
                count.put(codon,count.get(codon) + 1);
            else {
                count.put(codon,1);
                unique++;
            }
        }
    }
    private String getMostCommonCodon() {
        String maxStr = null;
        int max = 0;
        for(String s : count.keySet()) {
            if(max < count.get(s)) {
                max = count.get(s);
                maxStr = s;
            }
        }
        return maxStr;
    }
    public void printCodonCount(int start,int end) {
        for(String s : count.keySet()) {
            if(count.get(s) >= start && count.get(s) <= end)
                System.out.println("Codon : " + s + " Count : " + count.get(s));
        }
    }
    public void tester() {
        FileResource fr = new FileResource();
        buildCodon(1,fr.asString());
        printCodonCount(6,8);
        String maxStr = getMostCommonCodon();
        System.out.println("Max string : " + maxStr + " count : " + count.get(maxStr));
        System.out.println("No of unique of codons : " + unique);
    }
}