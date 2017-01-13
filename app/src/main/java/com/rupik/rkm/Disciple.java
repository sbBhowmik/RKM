package com.rupik.rkm;

/**
 * Created by macmin5 on 13/01/17.
 */

public class Disciple {
    String name;
    int imageResource;

    public  Disciple (String name, int imageResource)
    {
        this.name = name;
        this.imageResource = imageResource;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getName() {
        return name;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public void setName(String name) {
        this.name = name;
    }
}
