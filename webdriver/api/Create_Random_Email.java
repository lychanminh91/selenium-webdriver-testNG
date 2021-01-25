package api;

import java.util.Random;

public class Create_Random_Email {
    public static String generateEmail(){
        String characterForRandom = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder randomEmail= new StringBuilder();
        Random rnd = new Random();


        while (randomEmail.length()<8){
            int index = (int) (rnd.nextFloat()*characterForRandom.length());
            randomEmail.append(characterForRandom.charAt(index));
        }
        String createdEmail = randomEmail.toString();
        return createdEmail;
    }
}
