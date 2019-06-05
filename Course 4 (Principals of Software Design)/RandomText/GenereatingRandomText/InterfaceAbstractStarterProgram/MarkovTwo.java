
/**
 * Write a description of MarkovTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Random;
import java.util.*;     //for arraylist

public class MarkovTwo {
    private String myText;
    private Random myRandom;
    
    public MarkovTwo() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    //my getFollow
    private String getFollow(char ch) {
        StringBuilder follows = new StringBuilder();
        for(int i = 0;i < myText.length() - 1;i++) {
            if(myText.charAt(i) == ch) {
                follows.append(myText.charAt(i + 1));
            }
        }
        return follows.toString();
    }
    
    //There is a mistake in Owen's getFollows method. Think about how you should
    // define the position you start searching after each follow character is found.
    private ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length()) {
            int start = myText.indexOf(key,pos);
            if(start == -1) {
                break;
            }
            if(start + key.length() >= myText.length() - 1) {
                break;
            }
            String next = myText.substring(start + key.length(), start + key.length() + 1);
            follows.add(next);
            pos = start + key.length();
        }
        return follows;
    }
    
    public String getRandomText(int numChars){
        int markovNum = 2;
        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length() - markovNum);
        String key = myText.substring(index,index + markovNum);
        sb.append(key);
        
        for(int k = 0;k < numChars - markovNum;k++) {
            ArrayList<String> follows = getFollows(key);
            if(follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        return sb.toString();
        /*
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        char key = myText.charAt(myRandom.nextInt(myText.length()));
        sb.append(key);
        for(int k=0; k < numChars - 2; k++){
            String follows = getFollow(myText.charAt(k));
            int index = myRandom.nextInt(follows.length());
            char next = myText.charAt(index);
            sb.append(next);
            key = next;
        }
        return sb.toString();
        */
    }
}
