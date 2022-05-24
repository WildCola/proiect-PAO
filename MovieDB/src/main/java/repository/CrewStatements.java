package repository;
import services.DatabaseConfiguration;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CrewStatements {
    public static void createCrewTable(){
        String createTableSql = "CREATE TABLE IF NOT EXISTS crew" +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "name varchar(30))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
