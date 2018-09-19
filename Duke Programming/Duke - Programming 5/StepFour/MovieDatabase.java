//2018 Christopher Mogush
//MovieDatabase.java

//An efficient way to get information about movies
//Stores movie information in a HashMap for fast lookup of movie information given a movie ID
//Also allows filtering movies based on queries
//All methods and fields in the class are static. Can access methods in MovieDatabase without 
//using new to create objects, but by calling methods like MovieDatabase.getMovie("0120915")

import java.util.*;
import org.apache.commons.csv.*;
import edu.duke.FileResource;

public class MovieDatabase {
    private static HashMap<String, Movie> ourMovies; //maps a movie ID String to a Movie object with all the information about that movie
    public static void initialize(String moviefile) {
        if (ourMovies == null) { 
            ourMovies = new HashMap<String,Movie>();
            loadMovies("data/" + moviefile);
        }
    }

    private static void initialize() { //called as safety check to make sure there is movie data in the database.
        if (ourMovies == null) { 
            ourMovies = new HashMap<String,Movie>();
            loadMovies("data/ratedmoviesfull.csv"); 
        }
    }	

	
    private static void loadMovies(String filename) { //builds hashmap
        FirstRatings fr = new FirstRatings();
        ArrayList<Movie> list = fr.loadMovies(filename);
        for (Movie m : list) {
            ourMovies.put(m.getID(), m);
        }
    }

    public static boolean containsID(String id) { //returns true if id is in movie database, otherwise false
        initialize();
        return ourMovies.containsKey(id);
    }

    public static int getYear(String id) {
        initialize();
        return ourMovies.get(id).getYear();
    }

    public static String getGenres(String id) {
        initialize();
        return ourMovies.get(id).getGenres();
    }

    public static String getTitle(String id) {
        initialize();
        return ourMovies.get(id).getTitle();
    }

    public static Movie getMovie(String id) {
        initialize();
        return ourMovies.get(id);
    }

    public static String getPoster(String id) {
        initialize();
        return ourMovies.get(id).getPoster();
    }

    public static int getMinutes(String id) {
        initialize();
        return ourMovies.get(id).getMinutes();
    }

    public static String getCountry(String id) {
        initialize();
        return ourMovies.get(id).getCountry();
    }

    public static String getDirector(String id) {
        initialize();
        return ourMovies.get(id).getDirector();
    }

    public static int size() { //returns no. movies in database
        return ourMovies.size();
    }

    public static ArrayList<String> filterBy(Filter f) { 
        //returns an ArrayList of type String of movie IDs that match the filtering criteria.
        initialize();
        ArrayList<String> list = new ArrayList<String>();
        for(String id : ourMovies.keySet()) {
            if (f.satisfies(id)) {
                list.add(id);
            }
        }
        
        return list;
    }

}
