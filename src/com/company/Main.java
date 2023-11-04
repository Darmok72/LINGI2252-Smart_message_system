package com.company;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Main {


        /*
        selon le type de feature activé des commandes supplémentaires seront accessibles. si la feature n'est pas activée
        et que la commande est appelée alors il y aura un message d'erreur

         create_user user_name :  create user with name user_name
         add_friend user_name friend_name user_name : add friend friend_name to user user_name
         send_message user_name1 user_name2 "message" : send a from user_name1 to user_name2
         exit : end and close app

         // if the message send is the first we should create a new discussion, if not just add the message to the discussion

*/
        public static List<User> users;
        public static List<Discussion> discussions;

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
             users = new ArrayList<>();
             discussions = new ArrayList<>();

            while(true) {
                System.out.print("Enter a command: ");
                String input = scanner.nextLine();

                //    send_message user_name1 user_name2 "message" : send a from user_name1 to user_name2
                if (input.startsWith("send_message")) {
                    sendMessage(input);
                }
                //exit and close
                else if (input.startsWith("exit")) {
                    break;
                }
                // create_user user_name :  create user with name user_name
                else if (input.startsWith("create_user")) {
                    createUser(input);
                }

                else {
                    System.out.println("Invalid command");
                }
            }
            scanner.close();
        }

        // create_user user_name :  create user with name user_name
    public static void createUser(String input){
        Pattern pattern = Pattern.compile("create_user\\s+(\\w+)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            String name = matcher.group(1);

            // Check if the name is unique
            boolean isUnique = users.stream().noneMatch(user -> user.getName().equals(name));
            if (isUnique) {
                User newUser = new User(name);
                users.add(newUser);
                System.out.println("New user added: " + name);
            } else {
                System.out.println("User with the same name already exists.");
            }
        } else {
            System.out.println("Invalid create_user command format. It should be: create_user user_name");
        }
    }
    //send_message user_name1 user_name2 message : send a from user_name1 to user_name2
        public static void sendMessage(String input) {
            Pattern pattern = Pattern.compile("send_message\\s+(\\w+)\\s+(\\w+)\\s+\"([^\"]+)\"");
            Matcher matcher = pattern.matcher(input);
            boolean d_exist = false;
            if (matcher.matches()) {
                String user1 = matcher.group(1);
                String user2 = matcher.group(2);
                String message = matcher.group(3);
                // check if user1 or user2 dont exist
                boolean u1_exist = false;
                boolean u2_exist = false;
                for (User user: users) {
                    if (user1.equals(user.name)) u1_exist = true;
                    if (user2.equals(user.name)) u2_exist = true;
                }
                if(!u1_exist) System.out.println("user " + user1 + " does not exist");
                if(!u2_exist) System.out.println("user " + user2 + " does not exist");

                // create message
                Message m = new Message();
                m.date= new Date();
                m.content = message;

                //check if the discussion between the two user already exists
                //TODO for the moment here a discussion can have only two user
                //TODO verifiy if the contains correctly checks if the discussion exists and contain the users
                for (Discussion discussion: discussions) {
                    if (discussion.users_name.size()!=2 || !discussion.users_name.contains(user1) || !discussion.users_name.contains(user2)){
                       d_exist = true;
                        //discusison already exist
                        discussion.messages.put(m,m.date);
                    }
                }
                if(!d_exist){
                    //discussion dont exist, we must create it
                    Discussion discussion = new Discussion();
                    discussion.discussion(user1,user2);
                    discussion.messages.put(m,m.date);
                    discussions.add(discussion);
                }
                System.out.println("Sending from: " + user1 + " to: " + user2 + " with message: " + message);
            } else {
                System.out.println("Invalid send command format, must look like : send_message user_name1 user_name2 \"message\" ");
            }
        }
        public static User getUserByName(String name) {
                for (User user : users) {
                    if (user.getName().equals(name)) {
                        return user;
                    }
                }
                return null;
    }
        public static Discussion findDiscussionByUsers(String user1, String user2) {
                for (Discussion discussion : discussions) {
                    List<String> users = discussion.getUsers();
                    if (users.contains(user1) && users.contains(user2)) {
                        return discussion;
            }
        }
                return null;
    }
        

    }




