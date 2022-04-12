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

    public static Client createClient(List<Client> clientList){
        Scanner keyboard = new Scanner(System.in);
        String fname;
        String lname;
        String uname;
        String email;
        String password;
        System.out.println("Create new user\n");

        boolean ok = true;
        System.out.println("Email: ");
        email = keyboard.next();
        while(uniqueEmail(email, clientList) == false){
            System.out.println("There already exists an account with this email!");
            System.out.println("Email: ");
            email = keyboard.next();
        }

        System.out.println("Username: ");
        uname = keyboard.next();

        System.out.println("Password: ");
        password = keyboard.next();

        System.out.println("First name: ");
        fname = keyboard.next();

        System.out.println("Last name: ");
        lname = keyboard.next();

        Client c = new Client(uname, lname, fname, password, email);
        return c;
    }

//    public static Movie createMovie(){
//        Scanner keyboard = new Scanner(System.in);
//    }

    public static void listCLients(List<Client> clientList){
        System.out.println("Client list\n");
        Iterator<Client> i = clientList.iterator();
        while(i.hasNext()){
            Client client = i.next();
            userInfo(client);
            System.out.println();
        }
    }

    public static void movieInfo(Movie x){
        System.out.println(x.getTitle());
        System.out.println(x.getDirectors());
        System.out.println(x.getGenres());
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
                System.out.println(episodes.get(i).getTitle());
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

    public static void userInfo(User u){
        System.out.println("Username: " + u.getuName());
        System.out.println("Full Name: " + u.getfName() + " " + u.getlName());
        System.out.println("Email: " + u.getEmail());
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
