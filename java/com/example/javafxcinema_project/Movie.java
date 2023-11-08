package com.example.javafxcinema_project;

public class Movie {
    private final String title; // Movie name
    private final String image; // Movie image URL
    private final String classification; // Movie classification
    private final int duration; // Duration of the movie in minutes
    private final String genre; // Movie genre
    private final String about; // Movie description
    private final String trailer; // Movie trailer URL

    Movie(String title, String image, String classification, int duration, String genre, String about, String trailer) {
        this.title = title;
        this.image = image;
        this.classification = classification;
        this.duration = duration;
        this.genre = genre;
        this.about = about;
        this.trailer = trailer;
    }

    // getter:

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getClassification() {
        return classification;
    }

    public int getDuration() {
        return duration;
    }

    public String getGenre() {
        return genre;
    }

    public String getAbout() {
        return about;
    }

    public String getTrailer() {
        return trailer;
    }

}
