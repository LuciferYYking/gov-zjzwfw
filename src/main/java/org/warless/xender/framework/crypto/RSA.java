package org.warless.xender.framework.crypto;

import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * RSA
 *
 * @author :  Noa Swartz
 * @version : 1.0
 * @date : Created in 2019/5/7
 */
public class RSA {

    public static final String RSA = "RSA";
    public static final String ALGORITHM = "RSA/ECB/OAEPWITHSHA-512ANDMGF1PADDING";
    public static final String PUBLIC_FILE_SUFFIX = "pub";

    public PublicKey publicKey;
    private PrivateKey privateKey;

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void initKeys() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
            keyPairGenerator.initialize(4096);
            // Generate the KeyPair
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            // Get the public and private key
            publicKey = keyPair.getPublic();
            privateKey = keyPair.getPrivate();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public boolean exportKey(String fileName, Key key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        BigInteger modulus = null, exponent = null;
        if (key instanceof PublicKey) {
            RSAPublicKeySpec publicKeySpec = keyFactory.getKeySpec(key, RSAPublicKeySpec.class);
            modulus = publicKeySpec.getModulus();
            exponent = publicKeySpec.getPublicExponent();
        } else if(key instanceof PrivateKey) {
            RSAPrivateKeySpec privateKeySpec = keyFactory.getKeySpec(key, RSAPrivateKeySpec.class);
            modulus = privateKeySpec.getModulus();
            exponent = privateKeySpec.getPrivateExponent();
        }
        if (modulus != null && exponent != null) {
            try (ObjectOutputStream out = new ObjectOutputStream(
                    new BufferedOutputStream(new FileOutputStream(fileName)))) {
                out.writeObject(modulus);
                out.writeObject(exponent);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public byte[] encrypt(String plainText, Key key) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(plainText.getBytes());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String decrypt(byte[] cipherText, Key key) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedTextArray = cipher.doFinal(cipherText);
            return new String(decryptedTextArray);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] encrypt(String plainText, String keyPath) {
        return encrypt(plainText, loadKey(keyPath));
    }

    public String decrypt(byte[] cipherArray, String keyPath) {
        return decrypt(cipherArray, loadKey(keyPath));
    }

    private Key loadKey(String filePath) {
        Key key = null;
        try(ObjectInputStream oin = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filePath)))) {
            BigInteger modulus = (BigInteger) oin.readObject();
            BigInteger exponent = (BigInteger) oin.readObject();
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            key = filePath.endsWith(PUBLIC_FILE_SUFFIX) ? keyFactory.generatePublic(new RSAPublicKeySpec(modulus, exponent)) : keyFactory.generatePrivate(new RSAPrivateKeySpec(modulus, exponent));
        } catch (IOException | ClassNotFoundException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return key;
    }

    public static void main(String[] args) throws Exception {
        String plainText = "Hello World";
        RSA rsa = new RSA();
        rsa.initKeys();
        rsa.exportKey("id_rsa", rsa.getPrivateKey());
        rsa.exportKey("id_rsa.pub", rsa.getPublicKey());
        System.err.println("PlainText: " + plainText);
        byte[] cipherArray = rsa.encrypt(plainText, "id_rsa.pub");
        String encryptedText = Base64.getEncoder().encodeToString(cipherArray);
        System.err.println("CipherText: " + encryptedText);
        String decryptedText = rsa.decrypt(cipherArray, "id_rsa");
        System.err.println("DecryptedText: " + decryptedText);
    }

}
