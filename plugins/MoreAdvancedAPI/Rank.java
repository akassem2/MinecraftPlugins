package com.ayman.plugins.MoreAdvancedAPI;

public enum Rank {

    OWNER("&c&lOwner ", 'a'), //Bright red
    HELPER("&a&lHelper ", 'b'),
    MEMBER("&e&lMember ", 'c');

    private String display;
    private char orderSymbol; //Used to order the ranks alphabetically

    Rank(String display, char orderSymbol) {
        this.display = display;
        this.orderSymbol = orderSymbol;
    }

    public String getDisplay() { return display; }
    public char getOrderSymbol() { return orderSymbol; }

}
