package com.thoughtworks.gsahu.gameofthrones.beans;

/**
 * Created by gasahu on 08-Jan-17.
 */

public class King {
    private int kingID;
    private String kingName;
    private int rating;

    public int getKingID() {
        return kingID;
    }

    public void setKingID(int kingID) {
        this.kingID = kingID;
    }

    public String getKingName() {
        return kingName;
    }

    public void setKingName(String kingName) {
        this.kingName = kingName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
