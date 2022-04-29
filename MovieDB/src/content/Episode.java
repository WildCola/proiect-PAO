package content;

import java.util.List;

public class Episode extends Movie{
    private Show parentShow;

    public Episode(){
        super();
    }

    public Episode(String title, String date, Show parentShow){
        super(title, date);
        this.parentShow = parentShow;
    }

    public Episode(String title, List<Crew> directors, List<Crew> writers, List<Crew> producers, List<Crew> actors, List<Genre> genres, String date, Show parentShow){
        super(title, directors, writers, producers, actors, genres, date);
        this.parentShow = parentShow;
    }

    public Show getParentShow() {
        return parentShow;
    }

    public void setParentShow(Show parentShow) {
        this.parentShow = parentShow;
    }
}