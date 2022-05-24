package repository;
import services.DatabaseConfiguration;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EpisodeCrewJunction {
    public static void createEpisodeCrewTable(){
        String createTableSql = "CREATE TABLE IF NOT EXISTS episode_crew_junction" +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "episode_id int NOT NULL, " +
                "FOREIGN KEY (episode_id) REFERENCES episode(id)," +
                "crew_id int NOT NULL," +
                "FOREIGN KEY (crew_id) REFERENCES crew(id)," +
                "director bool," +
                "writer bool," +
                "producer bool," +
                "actor bool)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
