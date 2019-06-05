
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;


public class WordsInFiles {
    HashMap<String,ArrayList<String>> map;
    
    public WordsInFiles() {
        map = new HashMap<String,ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        //ArrayList<String> str = new ArrayList<String>();
        //str.clear();
        System.out.println(" ");
        //str.add(f.getName());
        //System.out.println(str);
        for(String word : fr.words()) {
            //System.out.println("LOL " + word);
            //System.out.print(!map.containsKey(word));
            //System.out.println(!((map.get(word)).contains(f.getName())));
            if(map.containsKey(word) == false) {
                map.put(word,new ArrayList<String>());  //if we write here map.put(word,str); then there is some error
                //map.get(word).add(f.getName());
                //System.out.println(1 + word + f.getName());
            }
            if(((map.get(word)).contains(f.getName())) == false){
                (map.get(word)).add(f.getName());
                //System.out.println(2 + word + f.getName());
            }
            //dispMap();
            //System.out.println("  for loop  ");
        }
        //dispMap();
        //System.out.println(fr.words());
    }
    
    private void clearFile(File f) {
        FileResource fr = new FileResource();
        for(String word : fr.words()) {
            map.get(word).clear();
        }
    }
    
    private void addFile(File f) {
        FileResource fr = new FileResource(f);
        for(String word : fr.words()) {
            //if(map.get(word).contains(f.getName()) == false) {
                map.get(word).add(f.getName());
                System.out.println("LOL " + word + "   " + f.getName());
            //}
        }
    }
    
    public void dispMap() {
        for(String s : map.keySet())
            System.out.println(" LOL " + s + map.get(s));
    }
    
    public void buildWordFileMap() {
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            addWordsFromFile(f);
            
            //addFile(f);
        }
        //for(File f : dr.selectedFiles()) {
          //  clearFile(f);
        //}
        //for(File f : dr.selectedFiles()) {
          //  addFile(f);
        //}
    }
    
    public int maxNumber() {
        int max = 0;
        String maxStr = null;
        for(String s : map.keySet()) {
            if(max < map.get(s).size()) {
                max = map.get(s).size();
                maxStr = s;
            }
        }
        return max;
    }
    
    public ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> str = new ArrayList<String>();
        for(String s : map.keySet()) {
            if(map.get(s).size() == number)
                str.add(s);
        }
        return str;
    }
    
    public void printFilesIn(String word) {
        if(map.get(word) != null)
            for(int i = 0;i < map.get(word).size();i++)
                System.out.println(map.get(word).get(i));
        else 
            System.out.println("No File");
    }
    
    public void tester() {
        buildWordFileMap();
        printFilesIn("laid");
        //for(String s : map.keySet())
          //  System.out.println(" LOL " + s + map.get(s));
        //System.out.println(map.get("silly").size());
        ArrayList<String> str = wordsInNumFiles(5);
        //for(int i = 0;i < str.size();i++)
        //    System.out.println(map.get(str.get(i)));
        System.out.println("Size str : " + str.size());
    }
}