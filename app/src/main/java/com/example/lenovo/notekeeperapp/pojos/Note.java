package com.example.lenovo.notekeeperapp.pojos;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by LENOVO on 29.04.2017.
 */
public class Note implements Serializable {
    static final long serialVersionUID = -1;



    private long id;
    private String title;
    private String description;
    private Date createDate;
    private int color;
    private boolean impotant;

    public Note() {
    }

    public Note(String title, String description, int color, boolean impotant) {
        this.title = title;
        this.description = description;
        this.color = color;
        this.impotant = impotant;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isImpotant() {
        return impotant;
    }

    public void setImpotant(boolean impotant) {
        this.impotant = impotant;
    }
}
