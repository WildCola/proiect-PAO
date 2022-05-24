package repository;

import content.Episode;
import content.Show;
import services.DatabaseConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class EpisodeStatements {
    public void createEpisodeTable(){
        String createTableSql = "CREATE TABLE IF NOT EXISTS episode" +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "title varchar(30), " +
                "genres json," +
                "ratings json," +
                "parent_show int," +
                "FOREIGN KEY (parent_show) REFERENCES serie(id)," +
                "releasedate date)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertEpisode(Episode episode){
        String query = "INSERT INTO episode(title, releasedate, parent_show) VALUES (?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, episode.getTitle());
            stmt.setDate(2, java.sql.Date.valueOf(episode.getDate()));
            stmt.setInt(3, episode.getParentShow().getId());

            stmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
