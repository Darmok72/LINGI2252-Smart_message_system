package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    public String email;
    public String name;
    public ArrayList<String> friend_list;
    public ArrayList<Discussion> discussion_list;


    public User(String n){
       name=n;
       friend_list = new ArrayList();
       discussion_list = new ArrayList();

    }
    public void add_friend(String s){
        friend_list.add((s));
    }

    public void remove_friend(String s){
        friend_list.remove(s);
    }

    public void set_nickname(String s){
        name = s;
    }

    public void add_discussion(Discussion d){
        discussion_list.add(d);
    }

    public ArrayList<Discussion> get_discussion(){
        return discussion_list;
    }

    public void remove_discussion(Discussion d){
        discussion_list.remove(d);
    }
    public String getName() {
        return name;
    }

    public void setName(String s) {
        name = s;
    }

}
