package com.serli.openstarsclient.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class SHA1PasswordEncoder implements PasswordEncoder{
    @Override
    public String encode(CharSequence rawPassword){
        return null;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String sha1;
        MessageDigest crypt;
        try {
            crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(rawPassword.toString().getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
            return sha1.equals(encodedPassword);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String byteToHex(final byte[] hash)
    {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
