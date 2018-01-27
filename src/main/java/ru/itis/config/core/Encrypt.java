package ru.itis.config.core;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Person_one on 24.03.2017.
 */
public class Encrypt {


    public static void main(String[] args) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String pass = "dima";
        String encodedPass = encoder.encode(pass);
        System.out.println("Secured password = "+encodedPass);
        System.out.println(encoder.matches(pass, encodedPass));

        //PasswordDecoder encoder0 = new BCryptPasswordDecoder();

    }
}