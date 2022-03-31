package content;

import java.util.*;

public class Show extends Movie{
    private List<Movie> episodes;

    public Show(){
        super();
    }


    public Show(String title, List<String> directors, String genre, String date, List<Movie> episodes) {
        super(title, directors, genre, date);
        this.episodes = episodes;
    }

    public List<Movie> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Movie> episodes) {
        this.episodes = episodes;
    }
}
