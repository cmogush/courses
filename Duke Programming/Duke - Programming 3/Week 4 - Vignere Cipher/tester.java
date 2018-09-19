//2017 Christopher Mogush
//test breakVignere()

import edu.duke.*;
import java.util.*;

public class tester {
    public void breakVigenere(){
        FileResource fr = new FileResource();
        String msg = fr.asString();
        
        //test CaeserCipher
        //int key = 12;
        //CaesarCipher cc = new CaesarCipher(key);
        //System.out.println(cc.encryptLetter('k'));
        //System.out.println(cc.decryptLetter('p'));
        //String encrypted = cc.encrypt(msg);
        //System.out.println(encrypted);
        //System.out.println(cc.decrypt(encrypted));
        
        //test CaeserCracker
        //CaesarCracker cracker = new CaesarCracker('e');
        //System.out.println(cracker.getKey(msg));
        //System.out.println(cracker.decrypt(msg));
        
        //test VigenereCipher
        //int vKey[] = {17, 14, 12, 4};
        //VigenereCipher vc = new VigenereCipher(vKey);
        //String encrypted = vc.encrypt(msg);
        //System.out.println(encrypted);
        //System.out.println(vc.decrypt(encrypted));
        
        //test VigenereBreaker
        System.out.println(msg);
        VigenereBreaker vb = new VigenereBreaker();
        vb.tryKeyLength(msg,5,'e');
    }
}
