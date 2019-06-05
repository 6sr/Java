
/**
 * Write a description of MarkovN here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Random;
import java.util.*;     //for arraylist

public class MarkovN {
    private String myText;
    private Random myRandom;
    private int markovNum;
    private HashMap<String,ArrayList<String>> map;
    
    public MarkovN(int n) {
        map = new HashMap<String,ArrayList<String>>();
        myRandom = new Random();
        markovNum = n;
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
            if(start < 0) {
                break;
            }
            if(start + key.length() >= myText.length()) {
                break;
            }
            String next = myText.substring(start + key.length(), start + key.length() + 1);
            follows.add(next);
            pos = start + key.length();
        }
        return follows;
    }
    
    public void buildMap(String key,HashSet<String> set) {
        ArrayList<String> follows = new ArrayList<String>();
        follows = getFollows(key);
        set.add(key);
        if(follows.size() == 0) {
            return;
        }
        for(int i = 0;i < follows.size();i++) {
            String pass = key.substring(1) + follows.get(i);
            set.add(pass);
            //buildMap(pass,set);
        }
    }
    public String getRandomText(int numChars,HashSet<String> set){
        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length() - markovNum);
        String key = myText.substring(index,index + markovNum);
        sb.append(key);
        
        for(int i = 0;i < myText.length() - markovNum;i++) {
            String curr = myText.substring(i,i + markovNum);
            buildMap(curr,set);
        }
        
        for(int k = 0;k < numChars - markovNum;k++) {
            ArrayList<String> follows = new ArrayList<String>();
            set.add(key);
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
            for(int i = 0;i < follows.size();i++) {
                set.add(key.substring(1) + follows.get(i));
            }
            sb.append(next);
            key = key.substring(1) + next;
        }
        System.out.println("Num of keys : " + map.size());
        System.out.println("Num of keys in set : " + set.size());
        return sb.toString();
    }
    
    public void test() {        //testgetfollowwithfile
        ArrayList<String> follow = getFollows("he");
        System.out.println("size of array list : " + follow.size());
    }
}