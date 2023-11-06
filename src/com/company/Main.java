package com.company;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {


        /*
        selon le type de feature activé des commandes supplémentaires seront accessibles. si la feature n'est pas activée
        et que la commande est appelée alors il y aura un message d'erreur

         Command List :
         create_user user_name                          create user with name user_name
         add_friend user_name friend_name user_name     add friend friend_name to user user_name
         send_message user_name1 user_name2 "message"   send a from user_name1 to user_name2
         create_call [ui]*                              create call  with optional users name
         activate_feature feature_name                  activate the feature_name (Call,Mute,Message_muter,Call_muter)
         mute_calls user_name                           mute the calls for this user_name
         unmute_calls user_name                         unmute the calls for this user_name
         mute_messages user_name                        mute messages for user_name
         unmute_messages user_name                      mute messages for user_name
         exit                                           end and close app


        // if the message send is the first we should create a new discussion, if not just add the message to the discussion
*/
        public static List<User> users;
        public static List<Discussion> discussions;
        public static List<Call> calls;

        //features
        public static boolean hasCall=false;
        public static boolean hasMute=false;
        public static boolean hasCallMuter=false;
        public static boolean hasMessageMuter=false;

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
             users = new ArrayList<>();
             discussions = new ArrayList<>();
             calls = new ArrayList<>();

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
                //   activate_feature feature_name : activate the feature_name (Call,Mute,Message_muter,Call_muter)
                else if (input.startsWith("activate_feature")) {
                    activateFeature(input);
                }
                //   create_call [ui]*: create call  with optional users name
                else if (input.startsWith("create_call")) {
                    //check if feature is enabled
                    if(hasCall){
                    createCall(input);
                } else{
                        System.out.println("Invalid command : The Call feature is not activated");
                    }
                }
                //mute_calls user_name : mute the calls for this user_name
                else if (input.startsWith("mute_calls")) {
                    //check if feature is enabled
                    if(hasCallMuter){
                        muteCall(input);
                    } else{
                        System.out.println("Invalid command : The  Call muter feature is not activated");
                    }
                }
                //unmute_calls user_name : unmute the calls for this user_name
                else if (input.startsWith("unmute_calls")) {
                    //check if feature is enabled
                    if(hasCallMuter){
                        unmuteCall(input);
                    } else{
                        System.out.println("Invalid command : The  Call muter feature is not activated");
                    }
                }
                //mute_messages user_name : mute messages for user_name
                else if(input.startsWith("mute_messages")){
                    //check if feature is enabled
                    if(hasMessageMuter){
                        muteMessage(input);
                    } else{
                        System.out.println("Invalid command : The  Message muter feature is not activated");
                    }
                }
                //unmute_messages user_name : mute messages for user_name
                else if (input.startsWith("unmute_messages")) {
                    //check if feature is enabled
                    if(hasMessageMuter){
                        unmuteMessage(input);
                    } else{
                        System.out.println("Invalid command : The  Message muter feature is not activated");
                    }
                }
                else {
                    System.out.println("Invalid command");
                }
            }
            scanner.close();
        }

    //mute_messages user_name : mute messages for user_name
    public static void muteMessage(String input){
        Pattern pattern = Pattern.compile("mute_messages\\s+(\\w+)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            String user_name = matcher.group(1);
            for (Call c: calls) {
                c.usersMessagesMute.add(user_name);
            }
        }
        else {
            System.out.println("Invalid command");
        }
    }
    //unmute_messages user_name : mute messages for user_name
    public static void unmuteMessage(String input){
        Pattern pattern = Pattern.compile("unmute_messages\\s+(\\w+)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            String user_name = matcher.group(1);
            for (Call c: calls) {
                c.usersMessagesMute.remove(user_name);
            }
        }
        else {
            System.out.println("Invalid command");
        }
    }

    //mute_calls user_name : mute the calls for this user_name
         public static void muteCall(String input){
             Pattern pattern = Pattern.compile("mute_calls\\s+(\\w+)");
             Matcher matcher = pattern.matcher(input);
             if (matcher.matches()) {
                 String user_name = matcher.group(1);
                 for (Call c: calls) {
                     c.usersCallMute.add(user_name);
                 }
             }
             else {
                 System.out.println("Invalid command");
             }
         }

         public static void unmuteCall(String input){
             Pattern pattern = Pattern.compile("unmute_calls\\s+(\\w+)");
             Matcher matcher = pattern.matcher(input);
             if (matcher.matches()) {
                 String user_name = matcher.group(1);
                 for (Call c: calls) {
                     c.usersCallMute.remove(user_name);
                 }
             }
             else {
                 System.out.println("Invalid command");
             }
         }


    //   activate_feature feature_name : activate the feature_name (Call,Mute,Message_muter,Call_muter)
        public static void activateFeature(String input){
            Pattern pattern = Pattern.compile("activate_feature\\s+(\\w+)");
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                String feature_name = matcher.group(1);
                if(feature_name.equals("Call")){hasCall=true;}
                if(feature_name.equals("Mute")){hasMute=true;}
                if(feature_name.equals("Message_muter")){hasMessageMuter=true;}
                if(feature_name.equals("Call_muter")){hasCallMuter=true;}
            }
            else {
                System.out.println("Invalid command");
            }
        }

        public static void createCall(String input){
            Pattern pattern = Pattern.compile( "create_call(?:\\s+\\w+)*");
            Matcher matcher = pattern.matcher(input);
            String[] users_in_call = new String[0];
            if (matcher.find()) {
                String usersString = matcher.group(1);
                 users_in_call = usersString.split("\\s+");
                Call new_call = new Call(hasMute,hasCallMuter,hasMessageMuter);
                new_call.users.addAll(Arrays.asList(users_in_call));
                calls.add(new_call);
            }
            else {
                System.out.println("Invalid command");
            }
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
              //  m.date= new Date();
                m.content = message;

                //check if the discussion between the two user already exists
                //TODO for the moment here a discussion can have only two user
                //TODO verifiy if the contains correctly checks if the discussion exists and contain the users
           /*     for (Discussion discussion: discussions) {
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
                */
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
      /*  public static Discussion findDiscussionByUsers(String user1, String user2) {
                for (Discussion discussion : discussions) {
                    List<String> users = discussion.getUsers();
                    if (users.contains(user1) && users.contains(user2)) {
                        return discussion;
            }
        }
                return null;
    }*/
        

    }




