package com.company;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

public class Discussion {
    public Hashtable<Message,Date> messages;
    public ArrayList<String> users;


    public void discussion(String a, String b){
        messages = new Hashtable<>();
        users = new ArrayList<>();
        users.add(a);
        users.add(b);
    }
    public boolean find_message(Message m){
        return messages.contains(m);
    }

    public void send(Message m){
        messages.put(m,m.date);
    }

    public void receive(Message m){
        messages.put(m,m.date);
    }
}
