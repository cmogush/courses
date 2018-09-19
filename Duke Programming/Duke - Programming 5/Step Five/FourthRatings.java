//2018 Christopher Mogush
//FourthRatings.java

import java.util.*;

public class FourthRatings {    
    public FourthRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public FourthRatings(String ratingsfile) {
        RaterDatabase.initialize(ratingsfile);
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
    
    private double dotProduct(Rater me, Rater r){
        //first translate a rating from the scale 0 to 10 to the scale -5 to 5
        //subract 5 from each rating to center them
        double dp = 0;
        ArrayList<String> moviesRated = me.getItemsRated();
        for(String m : moviesRated){
            if(r.getRating(m) != -1){ 
                dp += (me.getRating(m)-5) * (r.getRating(m)-5);
            }
        }
        //dot product = sum of rating products for each movie rated
        //return the dot product of the ratings of movies that they both rated
        return dp;
    }
    
    private ArrayList<Rating> getSimilarities(String id){
        /*computes a similarity rating for each rater in the RaterDatabase 
        (except the rater with the ID given by the parameter) to see how similar 
        they are to the Rater whose ID is the parameter to getSimilarities*/         
        ArrayList<Rating> similarities = new ArrayList<Rating>();
        ArrayList<Rater> raters = RaterDatabase.getRaters(); //get arraylist of raters to compare
        for(Rater r : raters){
            if(!r.getID().equals(id)){
                double dp = dotProduct(RaterDatabase.getRater(id), r);
                if(dp >= 0){ //only including those raters who have a positive similarity rating
                    Rating newRating = new Rating(r.getID(),dp);
                    similarities.add(newRating);
                }
            }
        }
        Collections.sort(similarities, Collections.reverseOrder());
        
        /*returns an ArrayList of type Rating sorted by ratings from highest to lowest rating 
         with the highest rating first and */
         
        //System.out.println(similarities);
        return similarities;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRatings, int minimalRaters){
        /*return an ArrayList of type Rating, of movies and their weighted average ratings using 
         only the top numSimilarRaters with positive ratings and including only those movies 
         that have at least minimalRaters ratings from those most similar raters 
         (not just minimalRaters ratings overall)*/
        ArrayList<Rating> ratingsList = new ArrayList<Rating>();
        ArrayList<Rating> similar = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        //sublist based on numSimilarRatings
        //ArrayList<Rating> topRaters = new ArrayList<Rating>(similar.subList(1, numSimilarRatings)); 
        
        /*For each movie, calculate a weighted average movie rating based on: Use only the top 
         * (largest) numSimilarRaters raters. For each of these raters, multiply their 
         * similarity rating by the rating they gave that movie*/
        
        for(String s : movies){
            //calculate avg weighted rating
            double sumRatings = 0;
            int numRaters = 0;
            //check movie list against topRater list
            for(int k=0;  k < numSimilarRatings; k++){ 
                String rID = similar.get(k).getItem(); //rater ID
                double weight = similar.get(k).getValue(); //rater weight
                Rater r = RaterDatabase.getRater(rID); //rater object
                if(r.getRating(s) >= 0){ //if the rater rated the movie, then...
                    numRaters += 1;  //add to the numRaters of the movie
                    sumRatings += (r.getRating(s)*weight); //add the weighted score to the ratings
                }
            }
            //check to see if enough topRaters rated the movie
            if(numRaters >= minimalRaters){
                Rating movieRating = new Rating(s, (sumRatings/numRaters)); //if so, get avg weight
                ratingsList.add(movieRating); //add weighted score to list
            }
        }
         
         
        //returned in sorted order by weighted average rating from largest to smallest ratings
        Collections.sort(ratingsList, Collections.reverseOrder());
        return ratingsList; 
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRatings, 
                                                        int minimalRaters, Filter filterCriteria){
        /*return an ArrayList of type Rating, of movies and their weighted average ratings using 
         only the top numSimilarRaters with positive ratings and including only those movies 
         that have at least minimalRaters ratings from those most similar raters 
         (not just minimalRaters ratings overall)*/
        ArrayList<Rating> ratingsList = new ArrayList<Rating>();
        ArrayList<Rating> similar = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        
        //sublist based on numSimilarRatings
        ArrayList<Rating> topRaters = new ArrayList<Rating>(similar.subList(1, numSimilarRatings)); 
        
        /*For each movie, calculate a weighted average movie rating based on: Use only the top 
         * (largest) numSimilarRaters raters. For each of these raters, multiply their 
         * similarity rating by the rating they gave that movie*/
        
        for(String s : movies){
            //calculate avg weighted rating
            double sumRatings = 0;
            int numRaters = 0;
            //check movie list against topRater list
            for(int k=0;  k < numSimilarRatings; k++){ 
                String rID = similar.get(k).getItem(); //rater ID
                double weight = similar.get(k).getValue(); //rater weight
                Rater r = RaterDatabase.getRater(rID); //rater object
                if(r.getRating(s) >= 0){ //if the rater rated the movie, then...
                    numRaters += 1;  //add to the numRaters of the movie
                    sumRatings += (r.getRating(s)*weight); //add the weighted score to the ratings
                }
            }
            //check to see if enough topRaters rated the movie
            if(numRaters >= minimalRaters){
                Rating movieRating = new Rating(s, (sumRatings/numRaters)); //if so, get avg weight
                ratingsList.add(movieRating); //add weighted score to list
            }
        }
         
         
        //returned in sorted order by weighted average rating from largest to smallest ratings
        Collections.sort(ratingsList, Collections.reverseOrder());
        return ratingsList; 
    }
    
    public void testGetSimilarRatings(){
        System.out.println(getSimilarRatings("1",10,2));
    }
}