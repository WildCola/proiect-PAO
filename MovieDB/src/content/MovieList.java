package content;

import users.User;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.*;

public class MovieList {
    private User creator;
    private Set<Movie> entries;
    private String createDate;
    private String updateDate;

    public MovieList(User creator, Set<Movie> entries) {
        this.creator = creator;
        this.entries = entries;
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
}
