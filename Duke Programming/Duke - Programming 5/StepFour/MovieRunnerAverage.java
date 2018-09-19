//2018 Christopher Mogush
//MovieRunnerAverage

import java.util.*;

public class MovieRunnerAverage {
    public void printAverageRatings(){
        //SecondRatings second = new SecondRatings("data/ratedmovies_short.CSV", "data/ratings_short.CSV");
        SecondRatings second = new SecondRatings("data/ratedmoviesfull.CSV", "data/ratings.CSV");
        System.out.println("No. movies: " + second.getMovieSize() + " | No. Raters: " 
        + second.getRaterSize());
        
        //code to print list of movies and their average ratings
        //must have at least minNoRatings and be sorted by avg
        int noRatings = 12;
        ArrayList<Rating> ratings = second.getAverageRatings(noRatings);
        System.out.println("No. movies with " + noRatings + " ratings: " + ratings.size());
        Collections.sort(ratings);
        for(Rating r : ratings){
            System.out.println(r.getValue() + " " + second.getTitle(r.getItem()));
        }
        
    }
    
    public void getAverageRatingOneMovie(){
        //SecondRatings second = new SecondRatings("data/ratedmovies_short_test.CSV", "data/ratings_short_test.CSV");
        //SecondRatings second = new SecondRatings("data/ratedmovies_short.CSV", "data/ratings_short.CSV");
        SecondRatings second = new SecondRatings("data/ratedmoviesfull.CSV", "data/ratings.CSV");
        //prints out the avg rating for a specific movie title
        String title = ("Inside Llewyn Davis");
        String id = second.getID(title);
        ArrayList<Rating> ratings = second.getAverageRatings(0);
        for(Rating r : ratings){
            if(second.getTitle(r.getItem()).equals(title)){
                System.out.println(r.getValue() + " " + second.getTitle(r.getItem()));
            }
        }
    }
}
