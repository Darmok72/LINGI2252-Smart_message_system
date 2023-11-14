package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
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
