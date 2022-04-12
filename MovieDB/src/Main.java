import content.*;
import services.*;
import users.*;

import java.util.*;


public class Main {
    public static void main(String[] args){
//        Crew cr1 = new Crew("bob");
//        List<Crew> regizori = new ArrayList<>();
//        regizori.add(cr1);
//        Genre g1 = new Genre("horror");
//        List<Genre> genres = new ArrayList<>();
//        genres.add(g1);
//        Show s1 = new Show();
//        Episode m1 = new Episode("titlu", regizori, genres, "01-01-2001", s1);
//        Movie m2 = new Movie();
//        List<Episode> ep = Collections.singletonList(m1);
//
//        s1 = new Show("show", regizori, genres, "05-05-2005",ep);
//
//        Service.addRating(m1, 5);
//        Service.addRating(m1, 2);
//        Service.movieInfo(m1);
//        Service.updateMovie(s1, "titluu");
//        Service.movieInfo(s1);
//        Service.listEpisodes(s1);
//
//        User u0 = new User();
//        User u1 = new User();
//        System.out.println(u1.getuName());
//
//        Set<Movie> mset = new HashSet<Movie>();
//        mset.add(m1);
//        mset.add(m2);
//
//
//        Service.addRating(m2, 5);
//        Service.addRating(m2, 5);
//        Movie m3 = new Movie("titlu3", regizori, genres, "data");
//        Service.addRating(m3, 1);
//
//        MovieList l1 = new MovieList(u0, mset);
//        Service.movielistInfo(l1);
//        Service.addMovie(l1, m3);
//        Service.sortascbyRating(l1);
//        Service.movielistInfo(l1);
//
//        Client c1 = new Client("client", "prenume", "nume", "parola", "email");
//        Service.userInfo(c1);

        List<Client> clients = new ArrayList<>();
        List<Movie> movies = new ArrayList<>();
        List<Show> series = new ArrayList<>();
        List<Crew> directors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        boolean ok = true;


        while(ok){
            System.out.println("MovieDB\n");
            System.out.println("1. Create User");
            System.out.println("2. Create Movie");
            System.out.println("3. Create Serie");
            System.out.println("4. Show Users");
            System.out.println("5. Show Movies");
            System.out.println("6. Show Series\n");

            Scanner keyboard = new Scanner(System.in);
            int choice = keyboard.nextInt();

            switch (choice) {
                default:
                    System.out.println("Goodbye!");
                    ok = false;
                    break;

                case 1:
                    Client newClient = Service.createClient(clients);
                    clients.add(newClient);
                    break;

                case 2:
                    //Movie newMovie =
                    break;

                case 4:
                    Service.listCLients(clients);
                    break;
            }
        }
    }
}
