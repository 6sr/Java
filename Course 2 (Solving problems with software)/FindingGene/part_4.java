
/**
 * Write a description of part_4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
//Printing selected links from list of links at URL
import edu.duke.*;

public class part_4 {
    public void readFile(URLResource URLFiles){
        int youtubeNo = 0;
        for(String word: URLFiles.words()){
            String lowercaseWord = word.toLowerCase();
            int youtubeIndex = lowercaseWord.indexOf("youtube.com");
            if(youtubeIndex != -1){ 
                int quotesFirstIndex = lowercaseWord.lastIndexOf("\"",youtubeIndex);
                int quotesLastIndex = lowercaseWord.indexOf("\"",youtubeIndex + 1);
                if(quotesFirstIndex != -1 && quotesLastIndex != -1){
                    System.out.println(word.substring(quotesFirstIndex,quotesLastIndex + 1));
                    youtubeNo++;
                }
            }    
        }
        System.out.println(youtubeNo);
    }
    public void testReadFile(){
        URLResource URLFiles = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        readFile(URLFiles);
    }
}