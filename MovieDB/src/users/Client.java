package users;

import content.Movie;

import java.util.*;

public class Client extends User{
    private boolean premium;
    private Set<Movie> watchedMov;
    private Set<Movie> watchList;
    private String address;
    private String card;

    public Client() {
        super();
        this.premium = false;
        this.address = "placeholder_address";
        this.card = "0000-0000-0000-0000";
    }

    public Client(String uName, String fName, String lName, String password, String email) {
        super(uName, fName, lName, password, email);
        this.premium = false;
        this.address = null;
        this.card = null;
    }

    public Client(String uName, String fName, String lName, String password, String email, boolean premium, String address, String card) {
        super(uName, fName, lName, password, email);
        this.premium = premium;
        this.address = address;
        this.card = card;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public Set<Movie> getWatchedMov() {
        return watchedMov;
    }

    public void setWatchedMov(Set<Movie> watchedMov) {
        this.watchedMov = watchedMov;
    }

    public Set<Movie> getWatchList() {
        return watchList;
    }

    public void setWatchList(Set<Movie> watchList) {
        this.watchList = watchList;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }
}
