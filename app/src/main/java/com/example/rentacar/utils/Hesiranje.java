package com.example.rentacar.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hesiranje {

    public static String generateSalt(int duzinaSalt) {
        String karakteri = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM0123456789!@$%^&*";
        StringBuilder st = new StringBuilder();
        for (int i = 0; i < duzinaSalt; i++) {
            st.append(karakteri.charAt((int) (Math.random() * karakteri.length())));
        }
        return st.toString();
    }

    public static String getSHA(String lozinka) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] messageDigest = md.digest(lozinka.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown for incorrect algorithm: " + e);
            return null;
        }
    }
}
