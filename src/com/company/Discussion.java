package com.company;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

public class Discussion {
    public Hashtable<Message,Date> messages;
    public ArrayList<String> users_name;

//TODO more than two users in discussion
    public void discussion(String a, String b){
        messages = new Hashtable<>();
        users_name = new ArrayList<>();
        users_name.add(a);
        users_name.add(b);
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
