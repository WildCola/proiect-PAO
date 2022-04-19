package services;

import content.*;
import users.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Service {

    public static boolean uniqueEmail(String email, List<Client> clientList){
        Iterator<Client> i = clientList.iterator();
        while (i.hasNext()) {
            Client client = i.next();
            if (email.equals(client.getEmail())) {
                return false;
            }
        }
        return true;
    }

    public static boolean uniqueMovie(String title, String date, List<Movie> movieList){
        Iterator<Movie> i = movieList.iterator();
        while (i.hasNext()){
            Movie movie = i.next();
            if(title.equals(movie.getTitle()) && date.equals(movie.getDate())){
                return false;
            }
        }
        return true;
    }

    public static boolean uniqueShow(String title, String date, List<Show> showList){
        Iterator<Show> i = showList.iterator();
        while (i.hasNext()){
            Show show = i.next();
            if(title.equals(show.getTitle()) && date.equals(show.getDate())){
                return false;
            }
        }
        return true;
    }

    public static boolean uniqueEpisode(String title, String date, List<Episode> episodeList){
        Iterator<Episode> i = episodeList.iterator();
        while (i.hasNext()){
            Episode episode = i.next();
            if(title.equals(episode.getTitle()) && date.equals(episode.getDate())){
                return false;
            }
        }
        return true;
    }

    public static Client createClient(List<Client> clientList){
        Scanner keyboard = new Scanner(System.in);
        String fname;
        String lname;
        String uname;
        String email;
        String password;
        System.out.println("Create new user\n");

        System.out.println("Email: ");
        email = keyboard.next();
        while(uniqueEmail(email, clientList) == false){
            System.out.println("There already exists an account with this email!");
            System.out.println("Email: ");
            email = keyboard.next();
        }

        System.out.println("Username: ");
        uname = keyboard.nextLine();

        System.out.println("Password: ");
        password = keyboard.next();

        System.out.println("First name: ");
        fname = keyboard.next();

        System.out.println("Last name: ");
        lname = keyboard.next();

        return new Client(uname, lname, fname, password, email);
    }

    public static Movie createMovie(List<Movie> movieList){ // Voi adauga membrii din crew la film cand voi avea baza de date. Vreau ca in momentul in care adaug de ex. un regizor la film, sa apara si filmul in lista de filme la care a lucrat regizorul
        Scanner keyboard = new Scanner(System.in);
        String title;
        String date;
        System.out.println("Create new movie\n");

        System.out.println("Title: ");
        title = keyboard.nextLine();

        System.out.println("Release date: ");
        date = keyboard.next();

        while (uniqueMovie(title, date, movieList) == false){
            System.out.println("This movie already exists!");
            System.out.println("1. Add different movie");
            System.out.println("2. Return to menu");
            int choice = keyboard.nextInt();
            switch (choice){
                case 1:
                    System.out.println("Title: ");
                    title = keyboard.nextLine();

                    System.out.println("Release date: ");
                    date = keyboard.next();
                    break;

                default:
                    return null;
            }
        }

        return new Movie(title, date);
    }

    public static Show createShow(List<Show> showList){
        Scanner keyboard = new Scanner(System.in);
        String title;
        String date;
        int episodeCount;
        List<Episode> episodes = new ArrayList<>();

        System.out.println("Title: ");
        title = keyboard.nextLine();

        System.out.println("Release date: ");
        date = keyboard.next();

        while (uniqueShow(title, date, showList) == false){
            System.out.println("This show already exists!");
            System.out.println("1. Add different show");
            System.out.println("2. Return to menu");
            int choice = keyboard.nextInt();
            switch (choice){
                case 1:
                    System.out.println("Title: ");
                    title = keyboard.nextLine();

                    System.out.println("Release date: ");
                    date = keyboard.next();
                    break;

                default:
                    return null;
            }
        }

        Show s = new Show(title, date, null);

        System.out.println("Number of episodes: ");
        episodeCount = keyboard.nextInt();
        for(int i=0; i<episodeCount; ++i){
            System.out.println("Episode " + (i+1) + ": ");
            episodes.add(createEpisode(episodes, s));
        }

        return new Show(title, date, episodes);
    }

    public static Episode createEpisode(List<Episode> episodeList, Show parentShow){
        Scanner keyboard = new Scanner(System.in);
        String title;
        String date;

        System.out.println("Title: ");
        title = keyboard.nextLine();

        System.out.println("Release date: ");
        date = keyboard.next();

        outerloop:
        while (uniqueEpisode(title, date, episodeList) == false){
            System.out.println("This episode already exists!");
            System.out.println("1. Add different episode");
            System.out.println("2. Return");
            int choice = keyboard.nextInt();
            switch (choice){
                case 1:
                    System.out.println("Title: ");
                    title = keyboard.nextLine();

                    System.out.println("Release date: ");
                    date = keyboard.next();
                    break;

                default:
                    return null;
            }
        }
        return new Episode(title, date, parentShow);
    }

    public static void listCLients(List<Client> clientList){
        System.out.println("Client list\n");
        Iterator<Client> i = clientList.iterator();
        while(i.hasNext()){
            Client client = i.next();
            userInfo(client);
            System.out.println();
        }
    }

    public static void listMovies(List<Movie> movieList){
        System.out.println("Movie list\n");
        Iterator<Movie> i = movieList.iterator();
        while (i.hasNext()){
            Movie movie = i.next();
            movieInfo(movie);
            System.out.println();
        }
    }

    public static void listShows(List<Show> showList){
        System.out.println("Show list\n");
        Iterator<Show> i = showList.iterator();
        while (i.hasNext()){
            Show show = i.next();
            showInfo(show);
            System.out.println();
        }
    }

    public static void userInfo(User x){
        System.out.println("Username: " + x.getuName());
        System.out.println("Full Name: " + x.getfName() + " " + x.getlName());
        System.out.println("Email: " + x.getEmail());
    }

    public static void movieInfo(Movie x){
        System.out.println("Title: " + x.getTitle());
        System.out.println("Date: " + x.getDate());

        List<Integer> ratings = x.getRatings();
        float r = 0;
        if(ratings != null)
        {
            for(int i = 0; i<ratings.size(); ++i){
                r += ratings.get(i);
            }
            r /= ratings.size();
            System.out.println(r);
        }
    }

    public static void showInfo(Show x){
        List<Episode> episodes = x.getEpisodes();
        System.out.println("Title: " + x.getTitle());
        System.out.println("Date: " + x.getDate());
        System.out.println("Number of episodes: " + episodes.size());
        System.out.println("Episodes: ");
        listEpisodes(x);
    }

    public static void addRating(Movie x, int r){
        List<Integer> ratings = x.getRatings();
        if(ratings == null)
            ratings = new ArrayList<Integer>();
        ratings.add(r);
        x.setRatings(ratings);
    }

    public static void updateMovie(Movie x, String title){
        x.setTitle(title);
    }

    public static void updateShow(Show x, String title, List<Episode> episodes){
        x.setTitle(title);
        x.setEpisodes(episodes);
    }

    public static void listEpisodes(Show x){
        List<Episode> episodes = x.getEpisodes();
        if(x == null)
            System.out.println("Episode list not found");
        else
            for(int i = 0; i < episodes.size(); ++i)
                System.out.println((i+1) + ": " + episodes.get(i).getTitle());
    }

    public static void movielistInfo(MovieList l){
        System.out.println("Creator: " + l.getCreator().getuName());

        System.out.println("Movies: ");
        Set<Movie> s = l.getEntries();
        Iterator<Movie> i = s.iterator();
        while(i.hasNext()){
            System.out.println();
            Movie m = i.next();
            movieInfo(m);

        }

        System.out.println("Created on " + l.getCreateDate());

        System.out.println("Last updated on " + l.getUpdateDate());
    }

    public static void addMovie(MovieList l, Movie m){
        Set<Movie> s = l.getEntries();
        s.add(m);
        l.setEntries(s);
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("E, MMM dd yyyy, hh:mm");
        l.setUpdateDate(localDate.format(myFormat));
    }

    public static void sortascbyRating(MovieList l){
        Set<Movie> s = l.getEntries();
        Iterator<Movie> i = s.iterator();
        TreeSet<Movie> ts = new TreeSet<>(new MovieComparator1());
        while(i.hasNext())
            ts.add(i.next());
        l.setEntries(ts);
    }

    public static void sortdescbyRating(MovieList l){
        Set<Movie> s = l.getEntries();
        Iterator<Movie> i = s.iterator();
        TreeSet<Movie> ts = new TreeSet<>(new MovieComparator2());
        while(i.hasNext())
            ts.add(i.next());
        l.setEntries(ts);
    }

    public static class MovieComparator1 implements Comparator<Movie> {
        @Override
        public int compare(Movie m1, Movie m2) {
            if(m1.getRatings() == null && m2.getRatings() == null)
                return 0;
            else if(m1.getRatings() == null)
                return -1;
            else if(m2.getRatings() == null)
                return 1;

            float r1=0, r2=0;
            for(int i = 0; i<m1.getRatings().size(); ++i){
                r1 += m1.getRatings().get(i);
            }
            r1 /= m1.getRatings().size();

            for(int i = 0; i<m2.getRatings().size(); ++i){
                r2 += m2.getRatings().get(i);
            }
            r2 /= m2.getRatings().size();

            if(r1 > r2)
                return 1;
            else if(r2 > r1)
                return -1;
            return 0;
        }
    }

    public static class MovieComparator2 implements Comparator<Movie> {
        @Override
        public int compare(Movie m1, Movie m2) {
            if(m1.getRatings() == null && m2.getRatings() == null)
                return 0;
            else if(m1.getRatings() == null)
                return 1;
            else if(m2.getRatings() == null)
                return -1;

            float r1=0, r2=0;
            for(int i = 0; i<m1.getRatings().size(); ++i){
                r1 += m1.getRatings().get(i);
            }
            r1 /= m1.getRatings().size();

            for(int i = 0; i<m2.getRatings().size(); ++i){
                r2 += m2.getRatings().get(i);
            }
            r2 /= m2.getRatings().size();

            if(r1 > r2)
                return -1;
            else if(r2 > r1)
                return 1;
            return 0;
        }
    }
}
