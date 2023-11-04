package com.company;

import java.util.ArrayList;
import java.util.Instant;
import java.util.Hashtable;
import java.util.List;

public class Discussion {
    public Hashtable<Message,Instant> messages;   
    public List<String> users;

//TODO more than two users in discussion
    public Discussion(List<String> participants){
        messages = new Hashtable<>();
        users = new ArrayList<>(participants);
    }
    public boolean find_message(Message m){
        return messages.containsKey(m);
    }

    public void addMessage(Message m){
        messages.put(m,Instant.now);
    }
    public void addUser(String userName) {
    users.add(userName);
}
}
