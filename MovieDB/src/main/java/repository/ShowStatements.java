package repository;
import services.DatabaseConfiguration;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ShowStatements {
    public static void createShowTable(){
        String createTableSql = "CREATE TABLE IF NOT EXISTS serie" +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "title varchar(30), " +
                "genres json," +
                "ratings json," +
                "episodes json," +
                "releasedate date)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
