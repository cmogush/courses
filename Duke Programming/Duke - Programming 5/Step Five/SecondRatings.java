//2018 Christopher Mogush
//SecondRatings.java

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    private FirstRatings first;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        first = new FirstRatings();
        myMovies = first.loadMovies(moviefile);
        myRaters = first.loadRaters(ratingsfile);
    }
    
    public int getMovieSize(){
        return myMovies.size();
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    
    /*
    private Double getAverageByID(String id, int minimalRaters){
        double average = 0.0;
        int noRatings = first.findNoRatingsForMovie(id, myRaters);
        if(noRatings >= minimalRaters){
            double culmRating = 0.0;
            for(Double d : first.listOfRatingsByID(id, myRaters)){
                culmRating += d;
            }
            average = culmRating / noRatings;
        }
        return average;
    }
    */
    
    private Double getAverageByID(String id, int minimalRaters){
        //get list of ratings for ID
        ArrayList<Double> ratings = new ArrayList<Double>();
        for(Rater r : myRaters){
            for(String s : r.getItemsRated()){
                if(s.equals(id)){ ratings.add(r.getRating(s));}
            }
        }
        
        //determine if average is greater than minimalRaters
        double average = 0.0;
        int noRatings = ratings.size();
        if(noRatings >= minimalRaters){
            double culmRating = 0.0;
            for(Double d : ratings){
                culmRating += d;
            }
            average = culmRating / noRatings;
        }
        //System.out.println(average);
        return average;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> ratingsList = new ArrayList<Rating>();
        //find avg rating for every movie, rated by at least minimalRaters
        for(Movie m : myMovies){
            Rating movieRating = new Rating(m.getID(), getAverageByID(m.getID(), minimalRaters));
            if(movieRating.getValue() > 0.0){ //0.0 is the value if it does not have > minimalRaters
                ratingsList.add(movieRating);
            }
        }
        //System.out.println(ratingsList);
        return ratingsList;
    }
    
    public String getTitle(String id){
        String title = "ID was not found";
        for(Movie m : myMovies){
            if(m.getID().equals(id)){ title = m.getTitle(); }
        }
        return title;
    }
    
    public String getID(String title){
        String id = "NO SUCH TITLE";
        for(Movie m : myMovies){
            if(m.getTitle().equals(title)){ id = m.getID(); }
        }
        return id;
    }
}