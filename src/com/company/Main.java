package com.company;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        /*
        selon le type de feature activé des commandes supplémentaires seront accessibles. si la feature n'est pas activée
        et que la commande est appelée alors il y aura un message d'erreur

         c user_name :  create user with name user_name
         a user_name friend_name r_name : add friend friend_name to user user_name
         s user_name1 user_name2 message : send a from user_name1 to user_name2

         // if the message send is the first we should create a new discussion, if not just add the message to the discussion

*/
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a command: ");
        String input = scanner.nextLine();

        Pattern pattern = Pattern.compile("test\\s+(\\w+)\\s+(\\w+)\\s+\"([^\"]+)\"");
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            String user1 = matcher.group(1);
            String user2 = matcher.group(2);
            String message = matcher.group(3);

            System.out.println(" user1: " + user1 + ", user2: " + user2 + ", message: " + message);
        } else {
            System.out.println("Invalid command format.");
        }

        scanner.close();
    }

}

