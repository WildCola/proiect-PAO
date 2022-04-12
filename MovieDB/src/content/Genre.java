package content;

import java.util.*;

public class Genre {
    private String name;
    private List<Movie> movies;
    private List<Show> shows;

    public Genre(){
        this.name = "placeholder_genre";
    }

    public Genre(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Show> getShows() {
        return shows;
    }
}
