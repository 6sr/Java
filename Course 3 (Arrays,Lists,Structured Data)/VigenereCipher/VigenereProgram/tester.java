
/**
 * Write a description of tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class tester {
    public void testVignereCipher() {
        int[] arr = {17,14,12,4};
        VigenereCipher vc = new VigenereCipher(arr);
        FileResource fr = new FileResource();
        String encrypted = vc.encrypt(fr.asString());
        System.out.println("Encrypted code : " + encrypted);
        System.out.println("Decrypted code : " + vc.decrypt(encrypted));
    }
    
    public void testVignereBreaker() {
        //int[] arr = {1,2,3,4};
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource();
        //String encrypted = vc.encrypt(fr.asString());
        //System.out.println(vb.sliceString("abcdefghijklm", 0, 3));
    }
}