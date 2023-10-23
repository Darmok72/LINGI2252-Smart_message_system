package com.company;

import java.util.ArrayList;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        // c_user user, a_friend(user,name), s_message(user,friend,message)

	// hardcoded test
        String[] l = {"c_user Billy", "a_friend Robert","s_message Robert,message divers"};

         ArrayList<User> user = new ArrayList<>();
        for (String s: l) {
            if (s.startsWith("c_user") ){
                User u = new User();
                u.user(s.substring(7));
                user.add(u);
            }
            else if (s.startsWith("a_friend")){
                user.get(0).add_friend(s.substring(9));
            }
            else if (s.startsWith("s_message")){
                Discussion d = new Discussion();
                d.discussion("Billy","Robert");
                Message m = new Message();
                m.content="message divers";
                m.date= new Date();
                d.send(m);
                user.get(0).add_discussion(d);
            }
        }

      //on vérifie
        System.out.println(user.get(0).name);
        System.out.println(user.get(0).friend_list.get(0));

    }
}
