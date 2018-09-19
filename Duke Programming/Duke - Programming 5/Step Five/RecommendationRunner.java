//2018 Christopher Mogush
//RecommendationRunner

import java.util.*;

public class RecommendationRunner implements Recommender{
    public ArrayList<String> getItemsToRate(){
        //setup
        ArrayList<String> list = new ArrayList<String>();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        AllFilters f = new AllFilters();
        ArrayList<String> filteredMovies = MovieDatabase.filterBy(f);
        
        //User selects # of movies to rate
        /*Scanner reader = new Scanner(System.in);
        System.out.println("How many movies would you like to rate? (min 10, max 20): ");
        int n = reader.nextInt();
        if(n>20){n = 20;} //failsafe for number of movies
        if(n<10){n = 10;}
        reader.close();*/
        int n = 10;
        //User selects critera
        //genre(s)
        //yearAfter
        //director(s)
        //runtime
                
        //get list of movies
        for(int k = 0; k < n; k++){ //populate list with random movies
            Random rand = new Random(); //seed of 10 for testing
            int r = rand.nextInt(MovieDatabase.size());
            int failsafe = 0;
            while(list.contains(r)){
                r = rand.nextInt(MovieDatabase.size());
                failsafe += 1;
                if(failsafe == 5){
                    System.out.println("Criteria is too specific. Please start over for complete list.");
                    break;
                }
            }
            list.add(filteredMovies.get(r));
        }
        
        return list; //list of movies for the user to rate
    }
    
    public void printRecommendationsFor(String webRaterID){
        //CSS Style
        
        
        FourthRatings fourth = new FourthRatings("ratings.csv");
        //FourthRatings fourth = new FourthRatings("ratings_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        
        //minimum noRatings for movies to be selected
        int min = 5;
        
        //minimum noTopRaters
        /* Scanner reader = new Scanner(System.in);
        System.out.println("How many Raters would you like to compare against? (min 10): ");
        int top = reader.nextInt();
        if(top<10){top = 10;}
        reader.close(); */
        int top = 1000;
        
        ArrayList<Rating> ratings = fourth.getSimilarRatings(webRaterID, top, min);
        
        Collections.sort(ratings, Collections.reverseOrder());
        
        
        
        //CSS Style
        System.out.println("<style>");
        System.out.println("#customers { font-family: \"Trebuchet MS\", Arial, Helvetica, sans-serif; border-collapse: collapse; width; }");
        System.out.println("#customers td, #customers th { border: 1px solid #ddd; padding: 8px;}");
        System.out.println("#customers tr:nth-child(even){background-color: #f2f2f2;}");
        System.out.println("#customers tr:hover {background-color: #ddd;}");
        System.out.println("#customers th { padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #4CAF50; color: white;}");
        System.out.println("</style>");
        
        //Page Content
        System.out.println("Christopher Mogush's recommendations for you:");
        System.out.println("<table id=\"customers\">");
        System.out.println("<tr><th>#</th><th>Poster</th><th>Title</th><th>Genre</th><th>Year</th><th>Time</th></tr>");
        for(int k=0;k<10;k++){
            Rating r = ratings.get(k);
            System.out.println("<tr><td>"+(k+1)+"</td>");
            System.out.println("<td><img src=\""+MovieDatabase.getPoster(r.getItem())+"\" height=157 width=100></td>");
            System.out.println("<td>"+MovieDatabase.getTitle(r.getItem())+"</td>");
            System.out.println("<td>"+MovieDatabase.getGenres(r.getItem())+"</td>");
            System.out.println("<td>"+MovieDatabase.getYear(r.getItem())+"</td>");
            System.out.println("<td>"+MovieDatabase.getMinutes(r.getItem())+"</td></tr>");
        }
        System.out.println("</table>");
    }
    /*
    public void tester(){
        RaterDatabase.initialize("ratings.csv");
        ArrayList<String> list = getItemsToRate();
        Rater webRater = new EfficientRater("999999");
        //get user movie ratings
        for(String s : list){
            System.out.println(MovieDatabase.getTitle(s));
            System.out.println("<img src=&#34;"+MovieDatabase.getPoster(s)+"&#34;>");
            Scanner reader = new Scanner(System.in);
            System.out.println("What would you rater this movie on a scale of 1-10 (0 if you haven't seen it): ");
            double r = reader.nextDouble();
            if(r>10){r = 10;} //failsafe for number of movies
            if(r<0){r = 0;}
            reader.close();
            RaterDatabase.addRaterRating("999999",s,r);
        }
        printRecommendationsFor("999999");
        
    }*/
}
