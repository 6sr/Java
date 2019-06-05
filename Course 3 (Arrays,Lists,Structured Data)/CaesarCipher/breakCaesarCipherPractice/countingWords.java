
/**
 * Write a description of countingWords here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class countingWords {
    public StringBuilder[] countWords(FileResource fr,int[] count) {
        StringBuilder[] words = new StringBuilder[100];
        //for(int i = 1;i < 100;i++)
          //  words[i] = new StringBuilder(" ");
        for(String word : fr.words()) {
            word = checkWord(word);
            
            count[word.length()]++;
        }
        return words;
    }
    public String checkWord(String check) {
        StringBuilder word = new StringBuilder(check);
        StringBuilder finalWord = new StringBuilder();
        for(int i = 0;i < word.length();i++) {
            char currChar = word.charAt(i);
            String currStr = (word.toString()).substring(i,i + 1);
            if(Character.isAlphabetic(currChar) || currChar == '-' || currStr.equals("'"))
                finalWord.append(currChar);
        }
        return finalWord.toString();
    }
    public void testCounter() {
        FileResource fr = new FileResource();
        int[] count = new int[30];
        StringBuilder[] words = new StringBuilder[30];
        words = countWords(fr,count);
        for(int i = 1;i < 30;i++) {
            if(count[i] != 0)
                System.out.println(count[i] + " words of length " + i + " : " + words[i]);
        }
    }
}