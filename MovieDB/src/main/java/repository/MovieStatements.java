package repository;
import content.Genre;
import services.DatabaseConfiguration;
import content.Movie;
import users.Client;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieStatements {
    public void createMovieTable(){
        String createTableSql = "CREATE TABLE IF NOT EXISTS movie" +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "title varchar(30), " +
                "genres json," +
                "ratings json," +
                "releasedate date)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertMovie(Movie movie){
        String query = "INSERT INTO movie(title, releasedate) VALUES (?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, movie.getTitle());

            stmt.setDate(2, java.sql.Date.valueOf(movie.getDate()));

            stmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteMovie(){
        String deleteSql = "DELETE FROM movie WHERE title=? AND releasedate=?";
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Title: ");
        String title = keyboard.nextLine();
        System.out.println("Release date (yyyy-mm-dd): ");
        String date = keyboard.nextLine();

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try(PreparedStatement stmt = connection.prepareStatement(deleteSql)) {
            stmt.setString(1, title);
            stmt.setDate(2, java.sql.Date.valueOf(date));

            stmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Movie> getAllMovies(){
        String query = "SELECT * FROM movie";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try(Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(query);
            List<Movie> movies = new ArrayList<Movie>();
            while (resultSet.next()){
                Date date = resultSet.getDate(5);
                String sdate = date.toString();
                Movie newMovie = new Movie(resultSet.getInt(1), resultSet.getString(2), sdate);
                movies.add(newMovie);
            }
            return movies;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
