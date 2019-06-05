
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;
import java.util.*;

public class MarkovRunner {
    public void runMarkovZero() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //st = "yes-this-is-a-thin-pretty-pink-thistle";
        //MarkovZero markov = new MarkovZero();
        //MarkovOne markov = new MarkovOne();
        //MarkovTwo markov = new MarkovTwo();
        MarkovN markov = new MarkovN(6);
        markov.setRandom(792);
        markov.setTraining(st);
        HashSet<String> set = new HashSet<String>();
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(100,set);
            printOut(text);
        }
        System.out.println("Size of set in runner: " + set.size());
        
        //test getfollowswithfile
        markov.test();
        int i = 0;
        int idx = st.indexOf("he");
        while(idx != -1) {
            i++;
            idx = st.indexOf("he",idx + 1);
        }
        System.out.println("i : " + i);
    }
    
    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
}