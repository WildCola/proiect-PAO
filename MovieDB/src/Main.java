import content.Movie;
import content.Show;
import services.Service;

import java.util.*;


public class Main {
    public static void main(String[] args){
        List<String> regizori = Collections.singletonList("regizor");
        Movie m1 = new Movie("titlu", regizori, "gen");
        List<Movie> ep = Collections.singletonList(m1);

        Show s1 = new Show("show", regizori, "genshow", ep);

        Service.addRating(s1, 5);
        Service.addRating(s1, 2);
        Service.movieInfo(s1);
        Service.updateMovie(s1, "titluu", m1.getDirectors(), m1.getGenre());
        Service.movieInfo(s1);
        Service.listEpisodes(s1);
    }
}
