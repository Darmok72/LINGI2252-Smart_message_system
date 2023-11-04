package com.company;

import java.util.Instant;

public class Message {
    public String content;
    public Instant date;

    public Object[] see_details(){
        return new Object[] {content, date};
    }

    public boolean equals(Message m){
        return m.content.equals(content);
    }
}
