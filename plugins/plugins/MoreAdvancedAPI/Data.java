package com.ayman.plugins.MoreAdvancedAPI;

import java.util.Date;

//FOR THE CUSTOM JSON FILES LECTURE
public class Data {

    private String playerName;
    private Boolean value;
    private Date date;

    public Data(String playerName, boolean value, Date date) {
        this.playerName = playerName;
        this.value = value;
        this.date = date;
    }

    public String getPlayerName() { return playerName; }
    public boolean getValue() {return value; }
    public Date getDate() {return date; }

}
