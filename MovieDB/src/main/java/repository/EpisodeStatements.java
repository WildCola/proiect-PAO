package repository;
import services.DatabaseConfiguration;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EpisodeStatements {
    public static void createEpisodeTable(){
        String createTableSql = "CREATE TABLE IF NOT EXISTS episode" +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "title varchar(30), " +
                "genres json," +
                "ratings json," +
                "serie json," +
                "releasedate date)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
