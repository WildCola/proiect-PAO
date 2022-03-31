package services;

import content.Movie;
import content.Show;

import java.util.*;

public class Service {

    public static void movieInfo(Movie x){
        System.out.println(x.getTitle());
        System.out.println(x.getDirectors());
        System.out.println(x.getGenre());
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

    public static void updateMovie(Movie x, String title, List<String> directors, String genre){
        x.setTitle(title);
        x.setDirectors(directors);
        x.setGenre(genre);
    }

    public static void updateShow(Show x, String title, List<String> directors, String genre, List<Movie> episodes){
        x.setTitle(title);
        x.setDirectors(directors);
        x.setGenre(genre);
        x.setEpisodes(episodes);
    }

    public static void listEpisodes(Show x){
        List<Movie> episodes = x.getEpisodes();
        if(x == null)
            System.out.println("Episode list not found");
        else
            for(int i = 0; i < episodes.size(); ++i)
                System.out.println(episodes.get(i).getTitle());
    }
}
