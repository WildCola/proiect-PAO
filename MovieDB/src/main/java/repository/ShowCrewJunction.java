package repository;
import services.DatabaseConfiguration;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ShowCrewJunction {
    public static void createShowCrewTable(){
        String createTableSql = "CREATE TABLE IF NOT EXISTS serie_crew_junction" +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "serie_id int NOT NULL, " +
                "FOREIGN KEY (serie_id) REFERENCES serie(id)," +
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
