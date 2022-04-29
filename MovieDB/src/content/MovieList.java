package content;

import users.Client;
import users.User;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.*;

public class MovieList {
    private Client creator;
    private Set<Movie> entries;
    private String createDate;
    private String updateDate;
    private String name;

    public MovieList(Client creator, Set<Movie> entries, String name) {
        this.creator = creator;
        this.entries = entries;
        this.name = name;
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("E, MMM dd yyyy, hh:mm");
        this.createDate = localDate.format(myFormat);
        this.updateDate = localDate.format(myFormat);
    }

    public User getCreator() {
        return creator;
    }

    public Set<Movie> getEntries() {
        return entries;
    }

    public void setEntries(Set<Movie> entries) {
        this.entries = entries;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
