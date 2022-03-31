import content.Movie;
import content.MovieList;
import content.Show;
import services.Service;
import users.Admin;
import users.Client;
import users.User;

import java.util.*;


public class Main {
    public static void main(String[] args){
        List<String> regizori = Collections.singletonList("regizor");
        Movie m1 = new Movie("titlu", regizori, "gen", "01-01-2001");
        Movie m2 = new Movie();
        List<Movie> ep = Collections.singletonList(m1);

        Show s1 = new Show("show", regizori, "genshow", "05-05-2005",ep);

        Service.addRating(m1, 5);
        Service.addRating(m1, 2);
        Service.movieInfo(m1);
        Service.updateMovie(s1, "titluu", m1.getDirectors(), m1.getGenre());
        Service.movieInfo(s1);
        Service.listEpisodes(s1);

        User u0 = new User();
        User u1 = new User();
        System.out.println(u1.getuName());

        Set<Movie> mset = new HashSet<Movie>();
        mset.add(m1);
        mset.add(m2);


        Service.addRating(m2, 5);
        Service.addRating(m2, 5);
        Movie m3 = new Movie("titlu3", regizori, "gen3", "data");
        Service.addRating(m3, 1);

        MovieList l1 = new MovieList(u0, mset);
        Service.movielistInfo(l1);
        Service.addMovie(l1, m3);
        Service.sortascbyRating(l1);
        Service.movielistInfo(l1);

        Client c1 = new Client("client", "prenume", "nume", "parola", "email");
        Service.userInfo(c1);
    }
}
