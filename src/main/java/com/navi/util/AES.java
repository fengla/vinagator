package com.navi.util;

import org.apache.commons.codec.binary.Base64;
import org.omg.CORBA.SystemException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class AES {

    private AES() {
        throw new AssertionError();
    }

    public static String genKey(byte []seed) throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128, new SecureRandom(seed));
        SecretKey key = keyGen.generateKey();
        return new String(Base64.encodeBase64(key.getEncoded()));
    }

    public static byte[] encrypt(byte []data, byte []key)
            throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, BadPaddingException,
            IllegalBlockSizeException {
        Cipher cipher =Cipher.getInstance("AES");
        SecretKeySpec k = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte []data, byte []key)
            throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, BadPaddingException,
            IllegalBlockSizeException {
        Cipher cipher =Cipher.getInstance("AES");
        SecretKeySpec k = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, k);

        return cipher.doFinal(data);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, IOException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256, new SecureRandom("yidianearthquake".getBytes()));
        SecretKey key1 = keyGen.generateKey();
        //System.out.println("key1:"+key1);

        //Cipher cipher = Cipher.getInstance("AES");
        //cipher.init(Cipher.ENCRYPT_MODE, key1);


        System.out.println(genKey("yidianearthquake".getBytes()));
//        String key = "o+gsehCBMgRcixhPCEKa0vm24E4+BPBTFweNBW0WRZo=";
        String key = genKey("yidianearthquake".getBytes());

        //String msg = "test";
        String msg = "{\"title\":\"测试标题1\",\"summary\":\"测试摘要1\",\"docId\":\"123456\",\"docType\":\"doc\",\"docUrl\":\"http://www.baidu.com\",\"tags\":\"标签1,tag2,标签3\",\"pushTime\":\"20170717\",\"pushType\":\"0\",\"timeStamp\":\"20170717888\"}";

        byte []keyBytes = Base64.decodeBase64(key);

        for (byte b : keyBytes) {
            System.out.print((int)b + " ");
        }
        System.out.println("");
        //加密
        byte[] encrypted = encrypt(msg.getBytes(), Base64.decodeBase64(key));

        System.out.println(encrypted);

        BASE64Encoder enc=new BASE64Encoder();
        String encrypted2=enc.encodeBuffer(encrypted);

        BASE64Decoder dec=new BASE64Decoder();
        //byte[] decrypted = decrypt(encrypted, Base64.decodeBase64(key));
        byte[] afterByte = dec.decodeBuffer(encrypted2);
        //解密
        byte[] decrypted = decrypt(afterByte, Base64.decodeBase64(key));
        System.out.println(new String(decrypted));
    }
}

