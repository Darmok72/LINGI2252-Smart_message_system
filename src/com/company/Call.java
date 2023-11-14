package com.company;

import java.util.HashSet;

public class Call {

    //users in the call
    public HashSet<String> users;

    //users that have joined the call
    public HashSet<String> users_calling;

    //users with the messages muted
    public HashSet<String> usersMessagesMute;

    //users with calls muted
    public HashSet<String> usersCallMute;

    //true if activated features
    public boolean hasMuteFeature;
    public boolean hasCallMuter;
    public boolean hasMessageMuter;

    public Call(boolean hasMuteFeature, boolean hasCallMuter,boolean hasMessageMuter){
        this.hasMuteFeature=hasMuteFeature;
        this.hasMessageMuter=hasMessageMuter;
        this.hasCallMuter=hasCallMuter;
        users = new HashSet<>();
        usersCallMute = new HashSet<>();
        usersMessagesMute = new HashSet<>();
        users_calling = new HashSet<>();
    }

}
