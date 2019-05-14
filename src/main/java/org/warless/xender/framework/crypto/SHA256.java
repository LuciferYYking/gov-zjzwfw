package org.warless.xender.framework.crypto;

import org.warless.xender.utils.CryptoUtils;


/**
 * Xender
 *
 * @author : Noa Swartz
 * @version : 1.0.0
 * @date : 2019/5/14
 */
public class SHA256 {



    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String hash = CryptoUtils.SHA256("F:\\Downloads\\win64_11gR2_database_1of2.zip");
        String hs = "6B762AF9825EE84565073322E11E941C61A5B3682DA0B990E76C1C5C918CB347";
        System.err.println(hs.equals(hash) + ": " + hash);
        long end = System.currentTimeMillis();
        System.err.println((end - start) / 1000.);
    }

}
