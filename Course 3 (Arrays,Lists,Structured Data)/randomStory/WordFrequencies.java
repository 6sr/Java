
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    int counter;
    public WordFrequencies() {
        counter = 0;
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for(String word : fr.words()) {
            word = word.toLowerCase();
            int idx = myWords.indexOf(word);
            if(idx == -1) {
                counter++;
                myWords.add(word);
                myFreqs.add(1);
            }
            else {
                int freq = myFreqs.get(idx);
                freq++;
                myFreqs.set(idx,freq);
            }
        }
    }
    public void tester() {
        findUnique();
        int max = 0,maxIdx = -1;
        System.out.println("No of unique words : " + myWords.size());
        for(int i = 0;i < myWords.size();i++) {
            System.out.println("Word : " + myWords.get(i) + " Freq : " + myFreqs.get(i));
            if(max < myFreqs.get(i)) {
                max = myFreqs.get(i);
                maxIdx = i;
            }
        }
        System.out.println("No of unique words : " + counter);
        System.out.println("Maximum freq word : " + myWords.get(maxIdx) + " at freq " + myFreqs.get(maxIdx));
    }
}