import content.*;
import services.*;
import users.*;
import repository.*;
import java.io.IOException;
import java.util.*;
import java.io.FileWriter;




public class Main {
    public static void main(String[] args) throws IOException {

        List<Client> clients = new ArrayList<>();
        List<Movie> movies = new ArrayList<>();
        List<Crew> directors = new ArrayList<>();
        List<Crew> producers = new ArrayList<>();
        List<Crew> writers = new ArrayList<>();
        List<Crew> actors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<Show> shows = new ArrayList<>();

//        CSV init
//        List<Client> clients = Service.clientReader();
//        List<Movie> movies = Service.movieReader();
//        List<Show> shows = Service.showReader();

//        SQL init
        ClientStatements clientStmt = new ClientStatements();
        clientStmt.createClientTable();
        clients = clientStmt.getAllClients();

        MovieStatements movieStmt = new MovieStatements();
        movieStmt.createMovieTable();




        List<MovieList> movieLists = Service.movieListReader(clients, movies);
        boolean ok = true;

        FileWriter auditWriter = Service.initializeWriter("audit.csv");

        while(ok){
            System.out.println("MovieDB\n");
            System.out.println("1. Create User");
            System.out.println("2. Create Movie");
            System.out.println("3. Create Serie");
            System.out.println("4. Show Users");
            System.out.println("5. Show Movies");
            System.out.println("6. Show Series");
            System.out.println("7. Show Movie Lists");
            System.out.println("8. Delete user");
            System.out.println("9. Change name");
            System.out.println("10. Delete movie");
            System.out.println("0. Exit\n");

            Scanner keyboard = new Scanner(System.in);
            int choice = keyboard.nextInt();


            switch (choice) {
                default:
                    System.out.println("Goodbye!");
                    ok = false;
                    break;

                case 1:
                    Client newClient = Service.createClient(clients);
                    clients.add(newClient);
                    clientStmt.insertClient(newClient);
                    clients = clientStmt.getAllClients();

                    Service.Audit("Add_Client", auditWriter);

                    break;

                case 2:
                    Movie newMovie = Service.createMovie(movies);
                    movies.add(newMovie);
                    movieStmt.insertMovie(newMovie);
                    movies = movieStmt.getAllMovies();

                    Service.Audit("Add_Movie", auditWriter);

                    break;

                case 3:
                    Show newShow = Service.createShow(shows);
                    shows.add(newShow);

                    Service.Audit("Add_Show", auditWriter);

                    break;

                case 4:
                    clients = clientStmt.getAllClients();
                    Service.listCLients(clients);

                    Service.Audit("List_Clients", auditWriter);

                    break;

                case 5:
                    movies = movieStmt.getAllMovies();
                    Service.listMovies(movies);

                    Service.Audit("List_Movies", auditWriter);

                    break;

                case 6:
                    Service.listShows(shows);

                    Service.Audit("List_Shows", auditWriter);
                    break;

                case 7:
                    Service.listLists(movieLists);
                    Service.Audit("List_MovieLists", auditWriter);
                    break;

                case 8:
                    clientStmt.deleteClient();
                    clients = clientStmt.getAllClients();
                    Service.Audit("Delete_Client", auditWriter);
                    break;

                case 9:

                    clientStmt.updateName();
                    clients = clientStmt.getAllClients();
                    Service.Audit("Update_Name", auditWriter);
                    break;

                case 10:
                    movieStmt.deleteMovie();
                    movies = movieStmt.getAllMovies();
                    Service.Audit("Delete_Movie", auditWriter);
                    break;
            }
        }
        Service.clientWriter(clients);
        Service.movieWriter(movies);
        Service.showWriter(shows);
        auditWriter.close();
        DatabaseConfiguration.closeDatabaseConnection();

    }
}
