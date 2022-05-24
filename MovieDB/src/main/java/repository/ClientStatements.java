package repository;
import services.DatabaseConfiguration;
import users.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientStatements {

    public void createClientTable(){
        String createTableSql = "CREATE TABLE IF NOT EXISTS client" +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "username varchar(30), " +
                "firstname varchar(30)," +
                "lastname varchar(30)," +
                "password varchar(30)," +
                "email varchar(30)," +
                "premium bool," +
                "watchedlist json," +
                "watchlist json," +
                "address varchar(30)," +
                "card varchar(16))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertClient(Client client){
        String insertClientSql = "INSERT INTO client(username, firstname, lastname, password, email, premium, address, card) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try(PreparedStatement stmt = connection.prepareStatement(insertClientSql)) {
            stmt.setString(1, client.getuName());
            stmt.setString(2, client.getfName());
            stmt.setString(3, client.getlName());
            stmt.setString(4, client.getPassword());
            stmt.setString(5, client.getEmail());
            stmt.setBoolean(6, client.isPremium());
            stmt.setString(7, client.getAddress());
            stmt.setString(8, client.getCard());

            stmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteClient(){
        String deleteSql = "DELETE FROM client WHERE username=?";
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Username: ");
        String uname = keyboard.nextLine();

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try(PreparedStatement stmt = connection.prepareStatement(deleteSql)) {
            stmt.setString(1, uname);

            stmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateName(){
        String query = "UPDATE client SET firstname=?, lastname=? WHERE id=?";
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Username: ");
        String uname = keyboard.nextLine();

        Client c = getClientByUsername(uname);

        System.out.println("New First name: ");
        String fn = keyboard.nextLine();
        System.out.println("New Last name: ");
        String ln = keyboard.nextLine();

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(3, c.getId());
            stmt.setString(1, fn);
            stmt.setString(2, ln);

            stmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Client> getAllClients(){
        String query = "SELECT * FROM client";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try(Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(query);
            List<Client> clients = new ArrayList<Client>();
            while (resultSet.next()){
                Client newClient = new Client(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getBoolean(7), resultSet.getString(10), resultSet.getString(11));
                clients.add(newClient);
            }
            return clients;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Client getClientByUsername(String uname){
        String selectSql = "SELECT * FROM client WHERE username=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try(PreparedStatement stmt = connection.prepareStatement(selectSql)) {
            stmt.setString(1, uname);

            ResultSet resultSet = stmt.executeQuery();
            return mapToClient(resultSet);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    private Client mapToClient(ResultSet resultSet) throws SQLException{
        if(resultSet.next()){
            return new Client(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getBoolean(7), resultSet.getString(8), resultSet.getString(9));
        }
        return null;
    }
}
