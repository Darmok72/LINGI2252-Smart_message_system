package com.company;

import java.util.HashSet;

public class Call {

    //users in the call
    public HashSet<String> users;

    //true if activated features
    public boolean hasMuteFeature;
    public boolean hasCallMuter;
    public boolean hasMessageMuter;

    //true if calls and messages are audible
    private boolean audibleMessage;
    private boolean audibleCalls;

    public Call(boolean hasMuteFeature, boolean hasCallMuter,boolean hasMessageMuter){
        this.hasMuteFeature=hasMuteFeature;
        this.hasMessageMuter=hasMessageMuter;
        this.hasCallMuter=hasCallMuter;
        users = new HashSet<>();
    }

    //TODO Calling and the muters
}
