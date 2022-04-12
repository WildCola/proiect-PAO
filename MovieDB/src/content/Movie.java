package content;

import java.util.*;

public class Movie {
    private String title;
    private List<Crew> directors;
    private List<Crew> writers;
    private List<Crew> producers;
    private List<Crew> actors;
    private List<Genre> genres;
    private List<Integer> ratings;
    private String date;

    public Movie() {
        this.title = "placeholder_title";
        this.date = "placeholder_date";
    }

    public Movie(String title, List<Crew> directors, List<Genre> genres, String date) {
        this.title = title;
        this.directors = directors;
        this.genres = genres;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Crew> getDirectors() {
        return directors;
    }

    public List<Crew> getWriters() {
        return writers;
    }

    public List<Crew> getProducers() {
        return producers;
    }

    public List<Crew> getActors() {
        return actors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Integer> getRatings() {
        return ratings;
    }

    public void setRatings(List<Integer> ratings) {
        this.ratings = ratings;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}

