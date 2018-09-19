//2018 Christopher Mogush
//MovieRunnerSimilarRatings

public class MovieRunnerSimilarRatings {

    public void printAverageRatings(){
        ThirdRatings third = new ThirdRatings("ratings.csv");
        //ThirdRatings third = new ThirdRatings("ratings_short.csv");
        System.out.println("read data for " + third.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        //code to print list of movies and their average ratings
        //must have at least minNoRatings and be sorted by avg
        int noRatings = 35;
        ArrayList<Rating> ratings = third.getAverageRatings(noRatings);
        System.out.println("Found " + ratings.size() + " movies with " + noRatings + " rating(s)");
        Collections.sort(ratings);
        for(Rating r : ratings){
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        ThirdRatings third = new ThirdRatings("ratings.csv");
        //ThirdRatings third = new ThirdRatings("ratings_short.csv");
        System.out.println("read data for " + third.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
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
        ArrayList<Rating> ratings = getAverageRatingsByFilter(noRatings, f, third); 
        
        //Print out # found matching criteria
        System.out.println("Found " + ratings.size() + " movies with " + noRatings 
            + " rating(s), made after year " + year + ", of genre: " + genre);
        //Sort list
        Collections.sort(ratings);
        //Print list of results
        for(Rating r : ratings){
            System.out.println(r.getValue() + " " + MovieDatabase.getYear(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(r.getItem()));
        }
    }
}