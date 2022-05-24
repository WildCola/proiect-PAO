package repository;
import services.DatabaseConfiguration;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminStatements {

    public static void createAdminTable(){
        String createTableSql = "CREATE TABLE IF NOT EXISTS admin" +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "username varchar(30), " +
                "firstname varchar(30)," +
                "lastname varchar(30)," +
                "password varchar(30)," +
                "email varchar(30)," +
                "start int," +
                "end int," +
                "authority bool)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
