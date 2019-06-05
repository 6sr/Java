
/**
 * Write a description of caesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class caesarCipher {
    public boolean isVowel(char ch) {
        if(ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U' || ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')
            return true;
        return false;
    }
    
    public void replaceVowels(String input,char ch) {
        StringBuilder str = new StringBuilder(input);
        for(int i = 0;i < str.length();i++)
            if(isVowel(str.charAt(i)))
                str.setCharAt(i,ch);
        System.out.println("Replaced vowels : " + str);        
    }
    
    public StringBuilder encrypt(String input,int key) {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";

        String newUpperAlpha = upper.substring(key) + upper.substring(0,key);
        String newLowerAlpha = lower.substring(key) + lower.substring(0,key);
        char ch;
        int currIndex;
        
        StringBuilder encrypt = new StringBuilder(input);
        StringBuilder newLower = new StringBuilder(newLowerAlpha);
        StringBuilder newUpper = new StringBuilder(newUpperAlpha);
        
        for(int i = 0;i < encrypt.length();i++) { //See This length() method
            ch = encrypt.charAt(i);
            if(Character.isLowerCase(ch)) {
                currIndex = lower.indexOf(ch);
                if(currIndex != -1)
                    encrypt.setCharAt(i,newLower.charAt(currIndex));
            }
            else if(Character.isUpperCase(ch)){
                currIndex = upper.indexOf(ch);
                if(currIndex != -1)
                    encrypt.setCharAt(i,newUpper.charAt(currIndex));
            }
        }
        return encrypt;
    }
    
    public void encryptTwoKeys(String input,int key1,int key2) {
        StringBuilder encrypt = new StringBuilder(input);
        int key = -1;
        StringBuilder ch = new StringBuilder();
        for(int i = 0;i < encrypt.length();i++) {
            if(i % 2 == 0)
                key = key1;
            else
                key = key2;
            ch = encrypt(input.substring(i,i + 1),key);
            encrypt.setCharAt(i,ch.charAt(0));
        }
        System.out.println("Encrypted by two keys : " + encrypt);
    }
    public void tester() {
        String input = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        
        //char ch = 'a';
        //replaceVowels(input,ch);
        
        int key = 15;
        StringBuilder encrypt = encrypt(input,key);
        System.out.println("Encrypted code : " + encrypt);
        
        StringBuilder decrypt = encrypt(encrypt.toString(),26 - key);
        System.out.println("Decrypted code : " + decrypt);

        int key1 = 21,key2 = 8;
        encryptTwoKeys(input,key1,key2);
    }
}