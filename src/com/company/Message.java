package com.company;

import java.time.Instant;

import java.util.Map;
import java.util.HashMap;

public class Message {
    public String content;
    public Instant date;

    public Object[] see_details(){
        return new Object[] {content, date};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return content.equals(message.content) && date.equals(message.date);
}
    public Map<String, Object> getDetails() {
        Map<String, Object> details = new HashMap<>();
        details.put("content", content);
        details.put("date", date);
        return details;
    }
}
