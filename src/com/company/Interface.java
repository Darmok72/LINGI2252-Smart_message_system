package com.company;

import java.awt.*;
import java.util.*;

public class Interface {
    private Frame frame;

    public boolean[] activated_features = {false,false,false,false};
    public String[] feature_names = {"Meeting","Dark Mode", "High Budget", "Premium" };

    public static Map<String, ArrayList<String>> user_informations;
    public String[] user_information_type = {"Email", "Contact"};
    public HashSet<String> all_users = new HashSet<>(Set.of("Richard", "Robert", "Camille"));

    public static Map<String, String> user_message;

    public static void main(String[] args) {
        //fill informations
        user_informations = new HashMap<>();
        user_informations.put("Robert", new ArrayList<> (Arrays.asList("robert12@gmail.com","Richard", "Camille")));
        user_informations.put("Richard", new ArrayList<> (Arrays.asList("richard12@gmail.com", "Camille")));
        user_informations.put("Camille", new ArrayList<> (Arrays.asList("camille12@gmail.com","Robert", "Richard")));
        new Interface();
    }
    public Interface() {
        ///global frame
        frame = new Frame("Interface");
        frame.setLayout(new FlowLayout());
        frame.setSize(400, 300);

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
    public void userSelection(){
        for (String u: all_users) {
            Button button = new Button(u);
            button.addActionListener(e -> {
                //create user_panel
                frame.removeAll(); // Remove all components from the frame
                userPanel(u);
                frame.revalidate(); // Revalidate the frame
                frame.repaint(); // Repaint the frame
            });
            frame.add(button, BorderLayout.CENTER);
        }

    }
    public void userPanel(String current_user){
        Label panel1 = new Label("Name: " +current_user);
        frame.add(panel1);
        for (int i =0 ;i<user_informations.get(current_user).size();i++) {
            int j = 1;
            if(i<=1){j=i;}
            Label panel2 = new Label(user_information_type[j]+": " +user_informations.get(current_user).get(i));
            frame.add(panel2,BorderLayout.AFTER_LAST_LINE);
        }
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
    }

    public void addContact(String current_user, String new_contact){
        if(all_users.contains(new_contact)){
            if(user_informations.get(current_user).contains(new_contact)){
                System.out.println("user already in contact");
            } else { user_informations.get(current_user).add(new_contact);}
        } else { System.out.println("user don't exist"); }

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
