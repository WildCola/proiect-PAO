package content;

import java.util.*;

public class Show extends Movie{
    private List<Episode> episodes;

    public Show(){
        super();
    }

    public Show(String title, List<Crew> directors, List<Crew> writers, List<Crew> producers, List<Crew> actors, List<Genre> genres, List<Integer> ratings, String date){
        super(title, directors, writers, producers, actors, genres, ratings, date);
    }

    public Show(String title, String date, List<Episode> episodes){
        super(title, date);
        this.episodes = episodes;
    }

    public Show(String title, List<Crew> directors, List<Crew> writers, List<Crew> producers, List<Crew> actors, List<Genre> genres, String date, List<Episode> episodes) {
        super(title, directors, writers, producers, actors, genres, date);
        this.episodes = episodes;
    }

    public Show(String title, List<Crew> directors, List<Crew> writers, List<Crew> producers, List<Crew> actors, List<Genre> genres, List<Integer> ratings, String date, List<Episode> episodes) {
        super(title, directors, writers, producers, actors, genres, ratings, date);
        this.episodes = episodes;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }
}
