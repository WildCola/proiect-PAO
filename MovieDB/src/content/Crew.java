package content;

import java.util.*;

public class Crew {
    private String fullName;
    private boolean actor;
    private boolean director;
    private boolean producer;
    private boolean writer;
    private List<Movie> actList;
    private List<Movie> dirList;
    private List<Movie> prodList;
    private List<Movie> wrtList;

    public Crew(){
        this.fullName = "placeholder_name";
    }

    public Crew(String fullName){
        this.fullName = fullName;
    }

    public boolean isActor() {
        return actor;
    }

    public boolean isDirector() {
        return director;
    }

    public boolean isProducer() {
        return producer;
    }

    public boolean isWriter() {
        return writer;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Movie> getActList() {
        return actList;
    }

    public List<Movie> getDirList() {
        return dirList;
    }

    public List<Movie> getProdList() {
        return prodList;
    }

    public List<Movie> getWrtList() {
        return wrtList;
    }

}

