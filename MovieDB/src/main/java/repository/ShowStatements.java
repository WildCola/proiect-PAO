package repository;

import content.Episode;
import content.Movie;
import content.Show;
import services.DatabaseConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShowStatements {
    public void createShowTable(){
        String createTableSql = "CREATE TABLE IF NOT EXISTS serie" +
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

    public void insertShow(Show show){
        String query = "INSERT INTO serie(title, releasedate) VALUES (?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, show.getTitle());
            stmt.setDate(2, java.sql.Date.valueOf(show.getDate()));

            stmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Show> getAllShows(){
        String query = "SELECT * FROM serie";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try(Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(query);
            List<Show> shows = new ArrayList<Show>();
            while (resultSet.next()){
                Date date = resultSet.getDate(5);
                String sdate = date.toString();
                int show_id = resultSet.getInt(1);

                List<Episode> episodes = new ArrayList<>();

                Show newShow = new Show(show_id, resultSet.getString(2), sdate, episodes);
                episodes = getEpisodes(newShow);
                newShow.setEpisodes(episodes);
                shows.add(newShow);
            }
            return shows;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    private List<Episode> getEpisodes(Show show){
        String query = "SELECT * FROM episode WHERE parent_show=" + show.getId();
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        List<Episode> episodes = new ArrayList<>();

        try(Statement stmt = connection.createStatement()){
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                Date date2 = resultSet.getDate(6);
                String sdate2 = date2.toString();
                Episode newEpisode = new Episode(resultSet.getInt(1), resultSet.getString(2), sdate2, show);
                episodes.add(newEpisode);
            }
            return episodes;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
