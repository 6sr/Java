
/**
 * Write a description of CharacterInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class CharacterInPlay {
    private ArrayList<String> name;
    private ArrayList<Integer> freqs;
    public CharacterInPlay() {
        name = new ArrayList<String>();
        freqs = new ArrayList<Integer>();
    }
    private void addToList(String currName) {
        int idx = name.indexOf(currName);
        if(idx == -1) {
            name.add(currName);
            freqs.add(1);
        }
        else {
            int freq = freqs.get(idx);
            freq++;
            freqs.set(idx,freq);
        }
    }
    private String findCharacter(String phrase) {
        String name = null;
        int index = phrase.indexOf(".");
        if(index != -1)
            name = phrase.substring(0,index);
        return name;
    }
    public void tester() {
        FileResource fr = new FileResource();
        for(String line : fr.lines()) {
            String currName = findCharacter(line);
            if(currName != null)
                addToList(currName);
        }
        for(int i = 0;i < name.size();i++)
            if(freqs.get(i) > 4)
                System.out.println("Name of character : " + name.get(i) + " and freq is " + freqs.get(i));
    }
}
