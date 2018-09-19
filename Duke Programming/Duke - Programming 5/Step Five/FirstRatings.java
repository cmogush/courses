//2018 Christopher Mogush
//FirstRatings

//Class to process movie and ratings data AND answer questions about them

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
import org.apache.commons.*;


public class FirstRatings {
     
    //processes every record from a CSV file
    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> movieData = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        //System.out.println(fr.asString());
        CSVParser parser = fr.getCSVParser();
        
        int noMovies = 0;
        int noComedies = 0;
        int noLengthGT150 = 0;
        int minutes = 0;
        for(CSVRecord rec : parser){
            //check for numeric value for minutes
            try{minutes = Integer.parseInt(rec.get("minutes"));}
            catch(NumberFormatException e){minutes = 0;}
            
            Movie film = new Movie(rec.get("id"),rec.get("title"),rec.get("year"),rec.get("genre"),rec.get("director"),
                rec.get("country"),rec.get("poster"),minutes);
            movieData.add(film);
            //System.out.println(film);            
        }
        return movieData;
    }
    
    public void printMovieData(ArrayList<Movie> movies){
        HashMap<String, ArrayList<String>> mapDirectors = new HashMap<String, ArrayList<String>>();
        //count
        int noMovies = 0;
        int noComedies = 0;
        int noLengthGT150 = 0;
        for(Movie m : movies){
            noMovies += 1;
            if(m.getGenres().contains("Comedy")){noComedies += 1;}
            if(m.getMinutes() > 150){noLengthGT150 += 1;}
            
            //build director hashmap
            String[] directors;
            directors = m.getDirector().split("//s+");
            
            //add movies to respective directors
            for(int k=0; k < directors.length; k++){
                if(!mapDirectors.containsKey(directors[k])){ //create an empty arraylist if key does not yet exist
                    ArrayList<String> moviesDirected = new ArrayList<String>();
                    mapDirectors.put(directors[k], moviesDirected);}
                mapDirectors.get(directors[k]).add(m.getTitle()); //add movie to director's arraylist
            }
        }
        
        //Maximum number of movies by 1 director
        int maxNoMoviesByOneDirector = 0;
        String directorWithMostMovies = "";
        for(String s : mapDirectors.keySet()){
            if(mapDirectors.get(s).size() > maxNoMoviesByOneDirector){
                maxNoMoviesByOneDirector = mapDirectors.get(s).size();
            }
        }
        
        //Readout movie data
        System.out.println("Found " + noMovies + " movies total");
        System.out.println("No. Comedies: " + noComedies);
        System.out.println("No. movies with runtime greater than 150 min: " + noLengthGT150);
        System.out.println("Director(s) with the greatest no. of movies (" + maxNoMoviesByOneDirector +"):");
        //print out directors with most movies
        for(String s : mapDirectors.keySet()){
            if(mapDirectors.get(s).size() == maxNoMoviesByOneDirector){
                System.out.println(s);
            }
        }
        System.out.println();
    }
       
    public ArrayList<Rater> loadRaters(String filename){
        ArrayList<Rater> raterData = new ArrayList<Rater>();
        FileResource fr = new FileResource(filename);
        //System.out.println(fr.asString());
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord rec : parser){
            String id = rec.get("rater_id");
            //if raterData does not yet contain rater
            boolean idExists = false;
            int currIndex = 0;
            for(Rater r : raterData){
                if(id.equals(r.getID())){
                    idExists = true;
                    currIndex = raterData.indexOf(r);
                    break;
                }
            }
            
            if(!idExists){
                //System.out.println("Creating new Rater record");
                Rater r = new EfficientRater(id);
                r.addRating(rec.get("movie_id"), Double.parseDouble(rec.get("rating")));
                raterData.add(r);
            }
            if(idExists){
                //System.out.println("Adding record to existing Rater");
                raterData.get(currIndex).addRating(rec.get("movie_id"), 
                Double.parseDouble(rec.get("rating")));
            }
            //System.out.println("ID: " + id + " | Movie: " + rec.get("movie_id") + " | Rating: " + rec.get("rating"));
            //System.out.println();
        }
        System.out.println("Finished Reading in Data");
        System.out.println();
        return raterData;
    }
    
    public void printRaterData(ArrayList<Rater> raters){
        System.out.println("Total number of raters: " + raters.size());
        for(Rater r : raters){
            System.out.println("Rater ID: " + r.getID() + " | No. Ratings: " + r.numRatings());
            for(String s : r.getItemsRated()){
                System.out.println("Movie ID: " + s + " | Rating: " + r.getRating(s));
            }
            System.out.println();
        }
    }
    
    public void printRatingsByID(String id, ArrayList<Rater> raterData){
        //check to see if rater id exists
        boolean idExists = false;
        int currIndex = 0;
        for(Rater r : raterData){
            if(id.equals(r.getID())){
                idExists = true;
                currIndex = raterData.indexOf(r);
                break;
            }
        }
        if(idExists){
            System.out.println("Printing rater data for id: " + id);
            Rater r = raterData.get(currIndex);
            System.out.println("Rater ID: " + r.getID() + " | No. Ratings: " + r.numRatings());
            for(String s : r.getItemsRated()){
                System.out.println("Movie ID: " + s + " | Rating: " + r.getRating(s));
            }
            System.out.println();
        }
        else{System.out.println("No rater could be found for ID: " + id); System.out.println();}
    }
    
    public int findMaxNoRatings(ArrayList<Rater> raterData){
        int maxNoRatings = 0;
        for(Rater r : raterData){
            if(r.numRatings() > maxNoRatings){ maxNoRatings = r.numRatings();}
        }            
        return maxNoRatings;
    }
    
    public ArrayList<String> findMaxRaters(ArrayList<Rater> raterData, int maxNoRatings){
        ArrayList<String> maxRaters = new ArrayList<String>();
        for(Rater r : raterData){
            if(r.numRatings() >= maxNoRatings){ maxRaters.add(r.getID());}
        }            
        return maxRaters;
    }
    
    public void printMaxRaters(ArrayList<Rater> raterData){
        int maxNoRatings = findMaxNoRatings(raterData);
        ArrayList<String> maxRaters = findMaxRaters(raterData, maxNoRatings);
        System.out.println("The maximum no. of ratings by any rater is: " + maxNoRatings);
        System.out.println("Raters who match maximum no: ");
        for(String s : maxRaters){
            System.out.println(s);
            printRatingsByID(s, raterData);
        }
        System.out.println();
    }
    
    public int findNoRatingsForMovie(String movieID, ArrayList<Rater> raterData){
        int noRatings = 0;
        for(Rater r : raterData){
            for(String s : r.getItemsRated()){
                if(s.equals(movieID)){ noRatings += 1;}
            }
        }
        return noRatings;
    }
    
    public int findNoMoviesRated(ArrayList<Rater> raterData){
        ArrayList<String> movies = new ArrayList<String>();
        for(Rater r : raterData){
            for(String s : r.getItemsRated()){
                if(!movies.contains(s)) movies.add(s);}
            }
        return movies.size();
    }
    
    /*
    public ArrayList<Double> listOfRatingsByID(String movieID, ArrayList<Rater> raterData){
        ArrayList<Double> ratings = new ArrayList<Double>();
        for(Rater r : raterData){
            for(String s : r.getItemsRated()){
                if(s.equals(movieID)){ ratings.add(Double.parseDouble(s));}
            }
        }
        return ratings;
    }
    */
    
    public void testLoadMovies(){
        /*
        ArrayList<Movie> moviesShort = loadMovies("data/ratedmovies_short.CSV");
        printMovieData(moviesShort);
        */
        ArrayList<Movie> moviesFull = loadMovies("data/ratedmoviesfull.CSV");
        printMovieData(moviesFull);
        
    }
    
    public void testLoadRaters(){
        
        ArrayList<Rater> ratersShort = loadRaters("data/ratings_short.CSV");
        printRaterData(ratersShort);
        /*
        printRatingsByID("2", ratersShort);
        printMaxRaters(ratersShort); 
        System.out.println("No. ratings for 1798709: " + findNoRatingsForMovie("1798709", ratersShort));
        System.out.println("No. unique movies rated: " + findNoMoviesRated(ratersShort));
        */
       /*
        ArrayList<Rater> ratersFull = loadRaters("data/ratings.CSV");
        //printRatingsByID("193",ratersFull);
        //printRaterData(ratersFull);
        //printMaxRaters(ratersFull);
        System.out.println("No. ratings for 1798709: " + findNoRatingsForMovie("1798709", ratersFull));
        System.out.println("No. unique movies rated: " + findNoMoviesRated(ratersFull)); 
        */
    }
}
