package com.createFunction;

import api.Create_Random_Email;

import java.util.Random;

public class Test_Function extends Create_Random_Email {
    public static void main(String[] args){
//        String characterForRandom = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
//        StringBuilder randomEmail = new StringBuilder();
//        Random rnd = new Random();
//
//        while (randomEmail.length()<10){
//        int index = (int) (rnd.nextFloat()*characterForRandom.length());
//        randomEmail.append(characterForRandom.charAt(index));
//        }
//        String createdEmail=randomEmail.toString();
//        System.out.println(createdEmail+"@gmail.com");
        System.out.println(generateEmail());

    }
}
