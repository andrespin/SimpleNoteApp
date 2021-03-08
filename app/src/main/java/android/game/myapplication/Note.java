package android.game.myapplication;

import java.io.Serializable;

public class Note implements Serializable {

    private String name;
    private String description;
    private String date;
    private int index;


    public Note(String name, String description, String date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public Note(String name, String description, String date, int index) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.index = index;
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

    public int getIndex() {
        return index;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}