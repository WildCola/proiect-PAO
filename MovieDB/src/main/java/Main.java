import content.*;
import services.*;
import users.*;
import java.io.IOException;
import java.util.*;
import java.io.FileWriter;




public class Main {
    public static void main(String[] args) throws IOException {

        List<Client> clients = Service.clientReader();
        List<Movie> movies = Service.movieReader();
        List<Show> shows = Service.showReader();

        List<Crew> directors = new ArrayList<>();
        List<Crew> producers = new ArrayList<>();
        List<Crew> writers = new ArrayList<>();
        List<Crew> actors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();

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

            System.out.println("0. Exit\n");

            Scanner keyboard = new Scanner(System.in);
            int choice = keyboard.nextInt();

            Service.createAdminTable();
            Service.createClientTable();
            Service.createMovieTable();
            Service.createEpisodeTable();
            Service.createShowTable();
            Service.createCrewTable();
            Service.createMovieCrewTable();
            Service.createShowCrewTable();
            Service.createEpisodeCrewTable();

            switch (choice) {
                default:
                    System.out.println("Goodbye!");
                    ok = false;
                    break;

                case 1:
                    Client newClient = Service.createClient(clients);
                    clients.add(newClient);

                    Service.Audit("Add_Client", auditWriter);

                    break;

                case 2:
                    Movie newMovie = Service.createMovie(movies);
                    movies.add(newMovie);

                    Service.Audit("Add_Movie", auditWriter);

                    break;

                case 3:
                    Show newShow = Service.createShow(shows);
                    shows.add(newShow);

                    Service.Audit("Add_Show", auditWriter);

                    break;

                case 4:
                    Service.listCLients(clients);

                    Service.Audit("List_Clients", auditWriter);

                    break;

                case 5:
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
            }
        }
        Service.clientWriter(clients);
        Service.movieWriter(movies);
        Service.showWriter(shows);
        auditWriter.close();
        DatabaseConfiguration.closeDatabaseConnection();

    }
}
