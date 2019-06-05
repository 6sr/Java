
/**
 * Write a description of caesarDecipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class caesarDecipher {
    public String splitString(String phrase,int begin) {
        StringBuilder part = new StringBuilder("");
        StringBuilder whole = new StringBuilder(phrase);
        //System.out.println(whole);
        for(int i = begin;i < whole.length();i += 2)
            part.append(whole.charAt(i));
        //System.out.println(part);
        return part.toString();
    }
    public int max(int[] arr,int len) {
        int max = arr[0];
        int maxIndex = 0;
        for(int i = 1;i < len;i++) {
            //System.out.print(arr[i] + "  ");
            if(max < arr[i]) {
                maxIndex = i;
                max = arr[i];
            }
            //System.out.print(max + "  ");    
        }
        //System.out.println(max);
        return maxIndex;
    }
    public int calMaxFreqIndex(String phrase) {
        int maxIndex;
        StringBuilder input = new StringBuilder(phrase);
        int[] arr = new int[26];
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        for(int i = 0;i < phrase.length();i++) {
            char ch = input.charAt(i);
            if(alpha.indexOf(ch) != -1) {
                arr[alpha.indexOf(ch)]++;
                //System.out.print(ch);
                //System.out.println(alpha.indexOf(ch));
            }
        }
        maxIndex = max(arr,26);
        //System.out.println("lol" + arr[maxIndex] + " " + input.charAt(maxIndex) +phrase.length() + " " + input.length() + input);
        return maxIndex;
    }
    public StringBuilder decipher(String phrase) {
        caesarCipher cc = new caesarCipher();
        int dKey = calMaxFreqIndex(phrase) - 4;
        if(dKey < 0)
            dKey += 26;
        System.out.println("\nKey : " + dKey);    
        return cc.encrypt(phrase,26 - dKey);
    }
    public void testDecipher() {
        //StringBuilder encrypt = new StringBuilder(" ");
        FileResource fr = new FileResource();
        String encrypt = fr.asString();
        
        System.out.println(encrypt + "\n\n\n");
        
        String a = splitString(encrypt,0);
        //System.out.println(a);
        StringBuilder part1 = decipher(a);
        //System.out.println(a + "    LOL     " + part1);
        String b = splitString(encrypt,1);
        //System.out.println(b);
        StringBuilder part2 = decipher(b);
        //System.out.println(part1 + "\nLOL\n");
        //System.out.println(part2 + "\n\n\n");
        
        int i;
        for(i = 0;i < part2.length();i++) {
            char ch1 = part1.charAt(i);
            char ch2 = part2.charAt(i);
            System.out.print(ch1);
            System.out.print(ch2);
        }
        //if(part1.length() != part2.length())
          //  System.out.println(part1.charAt(i));
        //System.out.println("LOL");    
    }
}