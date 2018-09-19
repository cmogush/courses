//2018 Christopher Mogush
//FourthRatings.java

import java.util.*;

public class FourthRatings {
    
    public FourthRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public FourthRatings(String ratingsfile) {
        RaterDatabase.initialize("data/" + ratingsfile);
    }
    
    public Double getAverageByID(String id, int minimalRaters){
             
        //get list of ratings for ID
        ArrayList<Double> ratings = new ArrayList<Double>();
        for(Rater r : RaterDatabase.getRaters()){
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
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String s : movies){
            //calculate avg rating
            Rating movieRating = new Rating(s, getAverageByID(s,minimalRaters));
            if(movieRating.getValue() > 0.0){ //0.0 is the value if it does not have > minimalRaters
                ratingsList.add(movieRating);
            }
        }
        //System.out.println(ratingsList);
        return ratingsList;
    }
    
     //public helper method
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        //create and return an ArrayList of type Rating of all the movies that have at 
        //least minimalRaters ratings and satisfies the filter criteria.
        
        //Create the ArrayList of type String of movie IDs from the MovieDatabase using the filterBy method
        ArrayList<String> filteredMovies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        for(String s : filteredMovies){
            //calculate avg rating
            Rating movieRating = new Rating(s, getAverageByID(s, minimalRaters));
            if(movieRating.getValue() > 0.0){ //0.0 is the value if it does not have > minimalRaters
                ratings.add(movieRating);
            }
        }
        return ratings;
    }
}