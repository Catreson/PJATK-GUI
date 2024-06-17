package com.pjatk_gui;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordManager {
    private static PasswordManager passwordManager;
    private PasswordManager(){}

    public static PasswordManager getPasswordManager(){
        if(passwordManager == null)
            passwordManager = new PasswordManager();
        return passwordManager;
    }

    public Password createPassword(char[] p, int iterations){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[32];
        random.nextBytes(salt);
        return new Password(iterations, salt, hashPassword(p, salt, iterations));
    }

    public byte[] hashPassword(char[] p, byte[] salt, int iterations){
        byte[] hash;
        KeySpec kS = new PBEKeySpec(p, salt, iterations, 256);
        SecretKeyFactory sKF;
        try {
            sKF = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hash = sKF.generateSecret(kS).getEncoded();
        } catch (Exception e) {
            hash = new byte[]{65, 66, 67};
            e.printStackTrace();
        }
        Arrays.fill(p, (char)0);
        return hash;
    }

    @SuppressWarnings("unlikely-arg-type")
    public boolean validatePassword(char[] p, Password hP){
        return hP.equals(hashPassword(p, hP.getSalt(), hP.getIterations()));
    }


    /*private byte[] makeHP(int iterations, byte[] salt, byte[] hash){
        byte[] bIterations = BigInteger.valueOf(iterations).toByteArray();
        byte[] hP = new byte[bIterations.length + salt.length + hash.length + 2];
        System.arraycopy(bIterations, 0, hP, 0, bIterations.length);
        hP[bIterations.length] = ':';
        System.arraycopy(salt, 0, hP, bIterations.length + 1, salt.length);
        hP[bIterations.length + salt.length + 1] = ':';
        System.arraycopy(hash, 0, hP, bIterations.length + salt.length + 2, hash.length);
        System.out.println("----------");
        System.out.println("Created hP: " + new String(hP, StandardCharsets.UTF_8));
        System.out.println("bIterations: " + bIterations.length);
        System.out.println("salt: " + salt.length);
        System.out.println("hash: " + hash.length);
        System.out.println("----------");
        return hP;
    }*/
}
