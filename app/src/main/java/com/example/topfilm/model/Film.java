package com.example.topfilm.model;

public class Film {
    private int id;
    private String point;
    private String name;
    private String introduce;
    private String url;

    public Film(int id, String point, String name, String introduce, String url) {
        this.id = id;
        this.point = point;
        this.name = name;
        this.introduce = introduce;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
