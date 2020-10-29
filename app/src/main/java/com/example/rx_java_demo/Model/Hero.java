package com.example.rx_java_demo.Model;

import com.google.gson.annotations.SerializedName;

public class Hero {


    public final String name;
    public final String realname;
    public final String team;
    private String firstappearance;
    public String createdby;
    private String publisher;
    private String imageurl;
    private String bio;


    public Hero(String name, String realname, String team, String firstappearance, String createdby, String publisher, String imageurl, String bio) {
        this.name = name;
        this.realname = realname;
        this.team = team;
        this.firstappearance = firstappearance;
        this.createdby = createdby;
        this.publisher = publisher;
        this.imageurl = imageurl;
        this.bio = bio;
    }

}
