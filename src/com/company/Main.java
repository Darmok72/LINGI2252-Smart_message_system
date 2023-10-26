package com.company;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {


        /*
        selon le type de feature activé des commandes supplémentaires seront accessibles. si la feature n'est pas activée
        et que la commande est appelée alors il y aura un message d'erreur

         c user_name :  create user with name user_name
         a user_name friend_name user_name : add friend friend_name to user user_name
         s user_name1 user_name2 message : send a from user_name1 to user_name2
         exit : end and close app

         // if the message send is the first we should create a new discussion, if not just add the message to the discussion

*/
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);


            while(true) {
                System.out.print("Enter a command: ");
                String input = scanner.nextLine();

                //    s user_name1 user_name2 message : send a from user_name1 to user_name2
                if (input.startsWith("s")) {
                    sendMessage(input);
                }
                //exit and close
                else if (input.startsWith("exit")) {
                    break;
                }
                // else if (input.startsWith(aaa"test2")) {
                //  processDeleteCommand(input);}
                else {
                    System.out.println("Invalid command");
                }
            }
            scanner.close();
        }

    //s user_name1 user_name2 message : send a from user_name1 to user_name2
        public static void sendMessage(String input) {
            Pattern pattern = Pattern.compile("s\\s+(\\w+)\\s+(\\w+)\\s+\"([^\"]+)\"");
            Matcher matcher = pattern.matcher(input);

            if (matcher.matches()) {
                String user1 = matcher.group(1);
                String user2 = matcher.group(2);
                String message = matcher.group(3);

                System.out.println("Sending from: " + user1 + " to: " + user2 + " with message: " + message);
            } else {
                System.out.print("Invalid add command format.");
            }
        }

    }




