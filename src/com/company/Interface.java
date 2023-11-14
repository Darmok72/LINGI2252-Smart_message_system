package com.company;

import java.awt.*;
import java.time.Instant;
import java.util.*;
import java.util.List;

public class Interface {
    private static Frame frame;

    //features
    public boolean[] activated_features = {false,false,false,false};
    public String[] feature_names = {"Meeting","Dark Mode", "High Budget", "Premium" };

    //users
    public static HashMap<String,User> all_users;
    //discussions
    // public static ArrayList<Discussion> discussion_list;

    public static void main(String[] args) {
        ///global frame
        frame = new Frame("Interface");
        frame.setLayout(new FlowLayout());
        frame.setSize(400, 300);

     //   discussion_list = new ArrayList<>();
        all_users = new HashMap<>();

        //placeholders
        User u1 =new User("Richard");
        u1.email = "robert12@gmail.com";
        u1.add_friend("Richard");
        User u2 = new User("Robert");
        u2.email = "richard12@gmail.com";
        u2.add_friend("Camille");
        User u3 = new User("Camille");
        u3.email = "camille12@gmail.com";
        u3.add_friend("Robert");
        all_users.put("Richard",u1);
        all_users.put("Robert",u2);
        all_users.put("Camille",u3);

        new Interface();
    }
    public Interface() {

        //create feature panel
        Label title = new Label("Choose activated features");
        frame.add(title,BorderLayout.NORTH);
        for(int i =0; i<feature_names.length;i++){
            feature_Button(i);
        }

        Button button = new Button("Done");
        button.addActionListener(e -> {
            //create user_panel
           frame.removeAll(); // Remove all components from the frame
            userSelection();
            frame.revalidate(); // Revalidate the frame
            frame.repaint(); // Repaint the frame
        });
        frame.add(button);
        frame.setVisible(true);
    }

    public void userSelection(){
        for (User u: all_users.values()) {
            Button button = new Button(u.name);
            button.addActionListener(e -> {
                //create user_panel
                frame.removeAll(); // Remove all components from the frame
                userPanel(u.name);
                frame.revalidate(); // Revalidate the frame
                frame.repaint(); // Repaint the frame
            });
            frame.add(button, BorderLayout.CENTER);
        }
        Button button2 = new Button("Back to feature selection");
        button2.addActionListener(e -> {
            //create user_panel
            frame.removeAll(); // Remove all components from the frame
            new Interface();
            frame.revalidate(); // Revalidate the frame
            frame.repaint(); // Repaint the frame
        });
        frame.add(button2, BorderLayout.CENTER);

    }
    public void userPanel(String current_user){
        Label panel1 = new Label("Name: " +current_user);
        Label panel2 =  new Label("Email: " +all_users.get(current_user).email);
        frame.add(panel1);
        frame.add(panel2);

        for (int i =0 ;i<all_users.get(current_user).friend_list.size();i++) {
            int j = 1;
            if(i<=1){j=i;}
            Label panel3 = new Label("Contact: " +all_users.get(current_user).friend_list.get(i));
            frame.add(panel3,BorderLayout.AFTER_LAST_LINE);
        }
        Button groupButton = new Button("Create Group");
        groupButton.addActionListener(e -> {
            //create user_panel
            frame.removeAll(); // Remove all components from the frame
         groupCreatorInput(current_user);
            frame.revalidate(); // Revalidate the frame
            frame.repaint(); // Repaint the frame
        });
        frame.add(groupButton, BorderLayout.CENTER);
        Button button = new Button("Back to user selection");
        button.addActionListener(e -> {
            //create user_panel
            frame.removeAll(); // Remove all components from the frame
            userSelection();
            frame.revalidate(); // Revalidate the frame
            frame.repaint(); // Repaint the frame
        });
        frame.add(button, BorderLayout.CENTER);
        TextField textField = new TextField("Type new contact");
        frame.add(textField, BorderLayout.CENTER);
        Button button2 = new Button("Add Contact");
        button2.addActionListener(e -> {
            String new_contact = textField.getText();
            addContact(current_user,new_contact);
        });
        frame.add(button2, BorderLayout.CENTER);

        Label discussions = new Label("Groups: ");
        frame.add(discussions, BorderLayout.CENTER);

        //TODO group members not showing
        for (Discussion group:all_users.get(current_user).discussion_list
             ) {
            Label discussions2 = new Label("Group Members: ");
            frame.add(discussions2, BorderLayout.CENTER);
            for (String u: group.users) {
                Label discussions3 = new Label(u);
                frame.add(discussions3, BorderLayout.CENTER);
            }
        }


    }

    public void addContact(String current_user, String new_contact){
        if(all_users.containsKey(new_contact)){
            if(all_users.get(current_user).friend_list.contains(new_contact)){
                System.out.println("user already in contact");
            } else {
                all_users.get(current_user).friend_list.add(new_contact);
                frame.removeAll(); // Remove all components from the frame
                userPanel(current_user);
                frame.revalidate(); // Revalidate the frame
                frame.repaint(); // Repaint the frame
                 }
        } else { System.out.println("user don't exist"); }

    }

    public void groupCreatorInput(String current_user){
        List<String> user_discussion_list=new ArrayList<>();

        for ( String contact:all_users.get(current_user).friend_list) {
            Button button = new Button(contact);
            button.setBackground(Color.RED);
            button.addActionListener(e -> {
                if(button.getBackground().equals(Color.RED)){
                    user_discussion_list.add(contact);
                    button.setBackground(Color.GREEN);
                } else {
                    user_discussion_list.remove(contact);
                    button.setBackground(Color.RED);
                }
            });
            frame.add(button, BorderLayout.CENTER);
        }


        Button button = new Button("Done");
        button.addActionListener(e -> {
          createGroup(user_discussion_list);
            frame.removeAll(); // Remove all components from the frame
            userPanel(current_user);
            frame.revalidate(); // Revalidate the frame
            frame.repaint(); // Repaint the frame
        });
        frame.add(button, BorderLayout.CENTER);
    }

    public void createGroup(List<String> users){
        Discussion new_discussion = new Discussion(users);
       // discussion_list.add(new_discussion);
        for (String user:users) {
           all_users.get(user).discussion_list.add(new_discussion);

        }
    }

    public void feature_Button(int i){
        Button button = new Button(feature_names[i]);
        button.setSize(10,10);
        if(activated_features[i]) {button.setBackground(Color.GREEN);}
        else{button.setBackground(Color.RED);}

        button.addActionListener(e -> {
            if(activated_features[i])
            {
                activated_features[i]=false;
                button.setBackground(Color.RED);
            }
            else{
                activated_features[i]=true;
                button.setBackground(Color.GREEN);
            }
        });
        frame.add(button, BorderLayout.CENTER);
    }
    public void sendmessage(String current_user, String dest_user){
        TextField textField = new TextField("Write Message");
        frame.add(textField, BorderLayout.CENTER);
        Button button2 = new Button("Send");
        button2.addActionListener(e -> {
            String message = textField.getText();
       //   addMessagetoDiscussion(current_user, dest_user, message);
        });
        frame.add(button2, BorderLayout.CENTER);


    }

}
