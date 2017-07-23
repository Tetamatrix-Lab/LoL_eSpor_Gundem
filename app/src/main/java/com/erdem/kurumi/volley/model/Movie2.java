package com.erdem.kurumi.volley.model;

public class Movie2 {
    private String title;//oyuncu adi
    private String thumbnailUrl;//resmi
    private String year;//takimadi
    private double rating;//kda
    private String link;

    public Movie2() {
    }

    public Movie2(String name, String thumbnailUrl, String year, double rating,String link) {
        this.title = name;
        this.thumbnailUrl = thumbnailUrl;
        this.year = year;
        this.rating = rating;
        this.setLink(link);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
