package services;

import content.*;
import users.*;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;


public class Service {

    public static List<Client> clientReader(){
        String line = "";
        List<Client> clients = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("clients.csv"));

            while ((line = br.readLine()) != null){
                String[] client = line.split(",");
                if (client.length == 5) {
                    clients.add(new Client(client[0], client[1], client[2], client[3], client[4]));
                }
                else {
                    clients.add(new Client(client[0], client[1], client[2], client[3], client[4], Boolean.parseBoolean(client[5]), client[6], client[7]));
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return clients;
    }

    public static List<Movie> movieReader(){
        String line = "";
        List<Movie> movies = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("movies.csv"));

            while ((line = br.readLine()) != null){
                String[] movie = line.split(",");
                List<Crew> directors = new ArrayList<>();
                List<Crew> producers = new ArrayList<>();
                List<Crew> actors = new ArrayList<>();
                List<Crew> writers = new ArrayList<>();
                List<Genre> genres = new ArrayList<>();
                List<Integer> ratings = new ArrayList<>();

                String[] info = movie[1].split("/");
                for(int i=0; i<info.length; ++i){
                    directors.add(new Crew(info[i]));
                }

                info = movie[2].split("/");
                for(int i=0; i<info.length; ++i){
                    writers.add(new Crew(info[i]));
                }

                info = movie[3].split("/");
                for(int i=0; i<info.length; ++i){
                    producers.add(new Crew(info[i]));
                }

                info = movie[4].split("/");
                for(int i=0; i<info.length; ++i){
                    actors.add(new Crew(info[i]));
                }

                info = movie[5].split("/");
                for(int i=0; i<info.length; ++i){
                    genres.add(new Genre(info[i]));
                }

                info = movie[6].split("/");
                for(int i=0; i<info.length; ++i){
                    ratings.add(Integer.parseInt(info[i]));
                }

                movies.add(new Movie(movie[0], directors, writers, producers, actors, genres, ratings, movie[7]));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return movies;
    }

    public static List<Show> showReader(){
        String line = "";
        List<Show> shows = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("shows.csv"));

            while ((line = br.readLine()) != null){
                String[] movie = line.split(",");
                List<Crew> directors = new ArrayList<>();
                List<Crew> producers = new ArrayList<>();
                List<Crew> actors = new ArrayList<>();
                List<Crew> writers = new ArrayList<>();
                List<Genre> genres = new ArrayList<>();
                List<Integer> ratings = new ArrayList<>();
                List<Episode> episodes = new ArrayList<>();

                String[] info = movie[1].split("/");
                for(int i=0; i<info.length; ++i){
                    directors.add(new Crew(info[i]));
                }

                info = movie[2].split("/");
                for(int i=0; i<info.length; ++i){
                    writers.add(new Crew(info[i]));
                }

                info = movie[3].split("/");
                for(int i=0; i<info.length; ++i){
                    producers.add(new Crew(info[i]));
                }

                info = movie[4].split("/");
                for(int i=0; i<info.length; ++i){
                    actors.add(new Crew(info[i]));
                }

                info = movie[5].split("/");
                for(int i=0; i<info.length; ++i){
                    genres.add(new Genre(info[i]));
                }

                info = movie[6].split("/");
                for(int i=0; i<info.length; ++i){
                    ratings.add(Integer.parseInt(info[i]));
                }
                Show newShow = new Show(movie[0], directors, writers, producers, actors, genres, ratings, movie[7]);

                info = movie[8].split("/");
                for(int i=0; i<info.length; i = i+2){
                    episodes.add(new Episode(info[i], info[i+1], newShow));
                }

                newShow.setEpisodes(episodes);
                shows.add(newShow);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return shows;
    }

    public static List<MovieList> movieListReader(List<Client> clients, List<Movie> movies){
        String line = "";
        List<MovieList> movieLists = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("movielists.csv"));

            while ((line = br.readLine()) != null){
                Client listCreator = null;
                String[] list = line.split(",");
                for (int i=0; i<clients.size(); ++i){
                    if (list[0].equals(clients.get(i).getuName())){
                        listCreator = clients.get(i);
                        break;
                    }
                }
                Set<Movie> listEntries = new HashSet<>();
                String[] titles = list[1].split("/");
                for(int i=0; i<titles.length; i+=2){
                    for(int j=0; j<movies.size(); ++j){
                        if(titles[i].equals(movies.get(j).getTitle()) && titles[i+1].equals(movies.get(j).getDate())){
                            listEntries.add(movies.get(j));
                            break;
                        }
                    }
                }

                movieLists.add(new MovieList(listCreator, listEntries, list[2]));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return movieLists;
    }

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
            clientInfo(client);
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

    public static void listLists(List<MovieList> movieLists){
        System.out.println("Movie lists\n");
        Iterator<MovieList> i = movieLists.iterator();
        while (i.hasNext()){
            MovieList list = i.next();
            movielistInfo(list);
            System.out.println();
        }
    }

    public static void userInfo(User x){
        System.out.println("Username: " + x.getuName());
        System.out.println("Full Name: " + x.getfName() + " " + x.getlName());
        System.out.println("Email: " + x.getEmail());
    }

    public static void clientInfo(Client x){
        System.out.println("Username: " + x.getuName());
        System.out.println("Full Name: " + x.getfName() + " " + x.getlName());
        System.out.println("Email: " + x.getEmail());
        if(x.isPremium() == true){
            System.out.println("Adresa: " + x.getAddress());
            System.out.println("Card: " + x.getCard());
        }
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
            System.out.println("Rating: " + r);
        }

        List<Crew> crew = x.getDirectors();
        String printCrew = "";
        if(crew != null){
            printCrew += crew.get(0).getFullName();
            for(int i=1; i<crew.size(); ++i){
                printCrew += ", " + crew.get(i).getFullName();
            }
            System.out.println("Directors: " + printCrew);
        }

        crew = x.getWriters();
        printCrew = "";
        if(crew != null){
            printCrew += crew.get(0).getFullName();
            for(int i=1; i<crew.size(); ++i){
                printCrew += ", " + crew.get(i).getFullName();
            }
            System.out.println("Writers: " + printCrew);
        }

        crew = x.getProducers();
        printCrew = "";
        if(crew != null){
            printCrew += crew.get(0).getFullName();
            for(int i=1; i<crew.size(); ++i){
                printCrew += ", " + crew.get(i).getFullName();
            }
            System.out.println("Producers: " + printCrew);
        }

        crew = x.getActors();
        printCrew = "";
        if(crew != null){
            printCrew += crew.get(0).getFullName();
            for(int i=1; i<crew.size(); ++i){
                printCrew += ", " + crew.get(i).getFullName();
            }
            System.out.println("Actors: " + printCrew);
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
        System.out.println("List name: " + l.getName());
        System.out.println("Creator: " + l.getCreator().getuName());

        System.out.println("Movies: ");
        Set<Movie> s = l.getEntries();
        Iterator<Movie> i = s.iterator();
        int nr = 0;
        while(i.hasNext()){
            System.out.println();
            nr++;
            System.out.println(nr + ".");
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
