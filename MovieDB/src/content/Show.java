package content;

import java.util.*;

public class Show extends Movie{
    private List<Episode> episodes;

    public Show(){
        super();
    }

    public Show(String title, List<Crew> directors, List<Genre> genres, String date, List<Episode> episodes) {
        super(title, directors, genres, date);
        this.episodes = episodes;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }
}
