import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder answer = new StringBuilder();
        for(int i = whichSlice;i < message.length();i += totalSlices)
            answer.append(message.charAt(i));
        return answer.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for(int i = 0;i < klength;i++)
            key[i] = cc.getKey(sliceString(encrypted,i,klength));
        //WRITE YOUR CODE HERE
        return key;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
        int max = 0;
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder letter = new StringBuilder(alphabet);
        int[] alpha = new int[26];
        for(int i = 0;i < 26;i++)
            alpha[i] = 0;
        for(String word : dictionary) {
            for(char c : word.toCharArray()) {
                int idx = alphabet.indexOf(Character.toLowerCase(c));
                if(idx != -1)
                    alpha[idx]++;
            }
        }
        char maxChar = 'e';
        for(int i = 0;i < 26;i++)
            if(max < alpha[i]) {
                maxChar = letter.charAt(i);
                max = alpha[i];
            }
        return maxChar;
    }
    
    public int countWords(String message,HashSet<String> dict) {
        int count = 0;
        for(String word : message.split("\\W+")) {
            if(dict.contains(word.toLowerCase()))
                count++;
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted,HashSet<String> dict) {
        String decrypted = new String();
        int max = 0;
        int idx = 0;
        char mostCommon = mostCommonCharIn(dict);
        for(int i = 1;i <= 100;i++) {
            int[] key = tryKeyLength(encrypted,i,mostCommon);
            VigenereCipher vc = new VigenereCipher(key); 
            String current = vc.decrypt(encrypted);
            int currRealWords = countWords(current,dict);
            //if(i == 38)
              //  System.out.println("Valid words for " + i + " is " + currRealWords);
            if(max <= currRealWords) {
                max = currRealWords;
                decrypted = current;
                idx = i;
            }
        }
        //System.out.println("Key Length : " + idx + "  Real words : " + max);
        return decrypted;
    }
    
    public void breakForAllLangs(String encrypted,HashMap<String,HashSet<String>> languages) {
        String decrypted = new String();
        int maxReal = 0;
        String maxLang = new String();
        for(String lang : languages.keySet()) {
            String current = breakForLanguage(encrypted,languages.get(lang));
            int currRealWords = countWords(current,languages.get(lang));
            if(maxReal < currRealWords) {
                maxReal = currRealWords;
                maxLang = lang;
                decrypted = current;
            }
        }
        System.out.println("Decrypted Code : " + decrypted);
        System.out.println("\nDecrypted Language : " + maxLang);
        System.out.println("\nReal Words : " + maxReal);
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dict = new HashSet<String>();
        //dict.add(new String());
        for(String word : fr.words()) {
            dict.add(word.toLowerCase());
            //System.out.println(word);
        }
        return dict;
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        //for(int i = 0;i < 1000;i++)
            //System.out.println("LOL");
       
       //Known key
       /*    
        FileResource fr = new FileResource();
        int[] key = tryKeyLength(fr.asString(),4,'e');
        VigenereCipher vc = new VigenereCipher(key);
        String decrypted = vc.decrypt(fr.asString()).substring(0,100);
        System.out.println("Decrypted code : " + decrypted);
        for(int i = 0;i < 4;i++)
            System.out.println(key[i] + ",");
        */
       
       //Unknown key Known lang
       /*
        FileResource fr = new FileResource();
        FileResource dictionary = new FileResource("dictionaries\\English");
        HashSet<String> dict = readDictionary(dictionary);
        String decrypted = breakForLanguage(fr.asString(),dict);
        System.out.println("Decrypted code : " + decrypted.substring(0,100));
        */
       
       //Check splitting
       /*
        System.out.println("Decryption");
        int i = 0;
        for(String word : decrypted.split("\\W+")) {
            System.out.println(word);
            i++;
        }
        System.out.println("No " + i);
        */
       
       //Unknown key Unknown lang
       FileResource fr = new FileResource();
       String[] lang = {"English","Danish","Dutch","French","German","Italian","Portuguese","Spanish"};
       HashMap<String,HashSet<String>> languages = new HashMap<String,HashSet<String>>();
       for(int i = 0;i < 8;i++) {
           FileResource dictionary = new FileResource("dictionaries\\" + lang[i]);
           HashSet<String> dict = readDictionary(dictionary);
           languages.put(lang[i],dict);
       }
       breakForAllLangs(fr.asString(),languages); 
    }
}