
/**
 * Write a description of MarkovN here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Random;
import java.util.*;     //for arraylist

public class MarkovN extends AbstractMarkovModel {
    private int markovNum;
    private HashMap<String,ArrayList<String>> map;
    public MarkovN(int n){
        markovNum = n;
        map = new HashMap<String,ArrayList<String>>();
    }

    //my getFollow
    /*
    private String getFollow(char ch) {
        StringBuilder follows = new StringBuilder();
        for(int i = 0;i < myText.length() - 1;i++) {
            if(myText.charAt(i) == ch) {
                follows.append(myText.charAt(i + 1));
            }
        }
        return follows.toString();
    }
    */
    
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
        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length() - markovNum);
        String key = myText.substring(index,index + markovNum);
        sb.append(key);
        
        for(int k = 0;k < numChars - markovNum;k++) {
            ArrayList<String> follows = new ArrayList<String>();
            if(!map.containsKey(key)) {
                follows = getFollows(key);
                map.put(key,follows);
            }
            follows = map.get(key);
            if(follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        printMap();
        return sb.toString();
    }
    
    public void printMap() {
        int maxSize = 0;
        for(String s : map.keySet()) {
            if(map.get(s).size() > maxSize) {
                maxSize = map.get(s).size();
            }
            System.out.println("Key : " + s + " Info : " + map.get(s));
        }
        System.out.print("Max Size of list in map : " + maxSize + " Key : ");
        for(String s : map.keySet()) {
            if(map.get(s).size() == maxSize) {
                System.out.print(s + ", ");
            }
        }
        System.out.println("\nNo of key : " + map.size());
    }
}