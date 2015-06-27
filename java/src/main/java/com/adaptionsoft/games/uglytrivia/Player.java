package com.adaptionsoft.games.uglytrivia;

public class Player {
    private String name = "Erik";
    private int purse = 0;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPurse() {
        return purse;
    }

    public void increasePurse() {
        purse++;
    }
}