//2018 Christopher Mogush
//MovieRunnerSimilarRatings

import java.util.*;

public class MovieRunnerSimilarRatings {    
    public void printAverageRatings(){
        FourthRatings fourth = new FourthRatings("ratings.csv");
        //ThirdRatings third = new ThirdRatings("ratings_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        
        //code to print list of movies and their average ratings
        //must have at least minNoRatings and be sorted by avg
        int noRatings = 35;
        ArrayList<Rating> ratings = fourth.getAverageRatings(noRatings);
        Collections.sort(ratings);
        for(Rating r : ratings){
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
        }
        
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println("Found " + ratings.size() + " movies with " + noRatings + " rating(s)");
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        FourthRatings fourth = new FourthRatings("ratings.csv");
        //FourthRatings fourth = new FourthRatings("ratings_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        
        //Criteria for filters
        int noRatings = 8; //must have at least minNoRatings and be sorted by avg
        int year = 1990;
        String genre = "Drama";
        //int minMinutes = 110;
        //int maxMinutes = 170;
        //String directors = "Charles Chaplin,Michael Mann,Spike Jonze";
        
        //AllFilters object to combine filters
        AllFilters f = new AllFilters();
        f.addFilter(new YearAfterFilter(year));
        f.addFilter(new GenreFilter(genre));
        
        //Get movies that match filter and no. ratings
        ArrayList<Rating> ratings = fourth.getAverageRatingsByFilter(noRatings, f); 
        
        //Sort list
        Collections.sort(ratings);
        //Print list of results
        for(Rating r : ratings){
            System.out.println(r.getValue() + " " + MovieDatabase.getYear(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(r.getItem()));
        }
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        //Print out # found matching criteria
        System.out.println("Found " + ratings.size() + " movies with " + noRatings 
            + " rating(s), made after year " + year + ", of genre: " + genre);
    }
    
    public void printSimilarRatings(){
        FourthRatings fourth = new FourthRatings("ratings.csv");
        //FourthRatings fourth = new FourthRatings("ratings_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        String id = "71";
        int noRatings = 5;
        int numSimilarRatings = 20;
        
        ArrayList<Rating> ratings = fourth.getSimilarRatings(id, numSimilarRatings, noRatings);
        
        Collections.sort(ratings, Collections.reverseOrder());
        for(Rating r : ratings){
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
        }
        
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        //Print out # found matching criteria
        System.out.println("Found " + ratings.size() + " movies with " + noRatings + " rating(s)"
        + " using " + numSimilarRatings + " top rater(s)");
    }
    
    public void printSimilarRatingsByGenre(){
        FourthRatings fourth = new FourthRatings("ratings.csv");
        //FourthRatings fourth = new FourthRatings("ratings_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        String id = "964";
        String genre = "Mystery";
        int noRatings = 5;
        int numSimilarRatings = 20;
        
        Filter f = new GenreFilter(genre);
        
        ArrayList<Rating> ratings = fourth.getSimilarRatingsByFilter(id, numSimilarRatings, noRatings, f);
        
        Collections.sort(ratings, Collections.reverseOrder());
        for(Rating r : ratings){
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
        }
        
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        //Print out # found matching criteria
        System.out.println("Found " + ratings.size() + " movies with " + noRatings + " rating(s)"
        + " using " + numSimilarRatings + " top rater(s)" + " with the genre " + genre);
    }
    
    public void printSimilarRatingsByDirector(){
        FourthRatings fourth = new FourthRatings("ratings.csv");
        //FourthRatings fourth = new FourthRatings("ratings_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        String id = "120";
        String directors = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
        int noRatings = 2;
        int numSimilarRatings = 10;
        
        Filter f = new DirectorsFilter(directors);
        
        ArrayList<Rating> ratings = fourth.getSimilarRatingsByFilter(id, numSimilarRatings, noRatings, f);
        
        Collections.sort(ratings, Collections.reverseOrder());
        for(Rating r : ratings){
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
        }
        
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        //Print out # found matching criteria
        System.out.println("Found " + ratings.size() + " movies with " + noRatings + " rating(s)"
        + " using " + numSimilarRatings + " top rater(s)");
        System.out.println("Directed by: " + directors);
    }
    
    public void printSimilarRatingsByGenreAndMinutes(){
        FourthRatings fourth = new FourthRatings("ratings.csv");
        //FourthRatings fourth = new FourthRatings("ratings_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        String id = "168";
        String genre = "Drama";
        int minMinutes = 80;
        int maxMinutes = 160;
        int noRatings = 3;
        int numSimilarRatings = 10;
        
        AllFilters f = new AllFilters();
        f.addFilter(new GenreFilter(genre));
        f.addFilter(new MinutesFilter(minMinutes, maxMinutes));
        
        ArrayList<Rating> ratings = fourth.getSimilarRatingsByFilter(id, numSimilarRatings, noRatings, f);
        
        Collections.sort(ratings, Collections.reverseOrder());
        for(Rating r : ratings){
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
        }
        
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        //Print out # found matching criteria
        System.out.println("Found " + ratings.size() + " movies with " + noRatings + " rating(s)"
        + " using " + numSimilarRatings + " top rater(s)");
        System.out.println("With genre " + genre + " and runtime between " + minMinutes + " and " + maxMinutes);
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes(){
        FourthRatings fourth = new FourthRatings("ratings.csv");
        //FourthRatings fourth = new FourthRatings("ratings_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        String id = "314";
        int year = 1975;
        int minMinutes = 70;
        int maxMinutes = 200;
        int noRatings = 5;
        int numSimilarRatings = 10;
        
        AllFilters f = new AllFilters();
        f.addFilter(new YearAfterFilter(year));
        f.addFilter(new MinutesFilter(minMinutes, maxMinutes));
        
        ArrayList<Rating> ratings = fourth.getSimilarRatingsByFilter(id, numSimilarRatings, noRatings, f);
        
        Collections.sort(ratings, Collections.reverseOrder());
        for(Rating r : ratings){
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
        }
        
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        //Print out # found matching criteria
        System.out.println("Found " + ratings.size() + " movies with " + noRatings + " rating(s)"
        + " using " + numSimilarRatings + " top rater(s)");
        System.out.println("Released after " + year + " and runtime between " + minMinutes + " and " + maxMinutes);
    }
}