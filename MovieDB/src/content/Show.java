package content;

import java.util.*;

public class Show extends Movie{
    private List<Movie> episodes;

    public Show(){
        this.title = "placeholder_title";
        this.directors = Collections.singletonList("placeholder_director");
        this.genre = "placeholder_genre";
    }


    public Show(String title, List<String> directors, String genre, List<Movie> episodes) {
        super(title, directors, genre);
        this.episodes = episodes;
    }

    public List<Movie> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Movie> episodes) {
        this.episodes = episodes;
    }
}
