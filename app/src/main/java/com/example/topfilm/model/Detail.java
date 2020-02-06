package com.example.topfilm.model;

public class Detail {
    private String name;
    private String urlTrailer;
    private String plot;
    private String actors;

    public Detail() {
    }

    public Detail(String name, String urlTrailer, String plot, String actors) {
        this.name = name;
        this.urlTrailer = urlTrailer;
        this.plot = plot;
        this.actors = actors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlTrailer() {
        return urlTrailer;
    }

    public void setUrlTrailer(String urlTrailer) {
        this.urlTrailer = urlTrailer;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }
}
