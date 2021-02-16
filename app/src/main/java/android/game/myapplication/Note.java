package android.game.myapplication;

import java.io.Serializable;

public class Note implements Serializable {

    private final String name;
    private final String description;
    private final String date;


    public Note(String name, String description, String date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }
}