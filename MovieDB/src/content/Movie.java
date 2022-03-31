package content;

import java.util.*;

public class Movie {
    protected String title;
    protected List<String> directors;
    protected String genre;
    protected List<Integer> ratings;

    public Movie() {
        this.title = "placeholder_title";
        this.directors = Collections.singletonList("placeholder_director");
        this.genre = "placeholder_genre";
    }

    public Movie(String title, List<String> directors, String genre) {
        this.title = title;
        this.directors = directors;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Integer> getRatings() {
        return ratings;
    }

    public void setRatings(List<Integer> ratings) {
        this.ratings = ratings;
    }

    public boolean gt(Movie x) {
        float r1=0, r2=0;
        for(int i = 0; i<this.ratings.size(); ++i){
            r1 += this.ratings.get(i);
        }
        r1 /= this.ratings.size();

        List<Integer> xratings = x.getRatings();

        for(int i = 0; i<xratings.size(); ++i){
            r2 += xratings.get(i);
        }
        r2 /= xratings.size();

        return r1 > r2;
    }
}

