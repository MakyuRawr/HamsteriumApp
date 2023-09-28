package com.makyu.hamsterium;

public class Hamster {
    private String name;
    private int imageResourceId;
    private String category;

    public Hamster(String name, int imageResourceId, String category) {
        this.name = name;
        this.imageResourceId = imageResourceId;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}