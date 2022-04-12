package content;

import java.util.List;

public class Episode extends Movie{
    private Show parentShow;

    public Episode(){
        super();
    }

    public Episode(String title, List<Crew> directors, List<Genre> genres, String date, Show parentShow){
        super(title, directors, genres, date);
        this.parentShow = parentShow;
    }

    public Show getParentShow() {
        return parentShow;
    }

    public void setParentShow(Show parentShow) {
        this.parentShow = parentShow;
    }
}
