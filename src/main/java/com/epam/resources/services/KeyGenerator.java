package com.epam.resources.services;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class KeyGenerator {
    public String generateKey() {
        Random rnd = new Random();
        int number = rnd.nextInt(99999999);

        // this will convert any number sequence into 6 character.
        return String.format("%08d", number);
    }
}
