package com.company;

import java.util.Date;

public class Message {
    public String content;
    public Date date;

    public Object[] see_details(){
        return new Object[] {content, date};
    }

    public boolean equals(Message m){
        return m.content.equals(content);
    }
}
