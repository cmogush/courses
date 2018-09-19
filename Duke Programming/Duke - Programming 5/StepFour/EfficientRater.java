//2018 Christopher Mogush
//EfficientRater.java
//class that keeps track of one rater and all their ratings

import java.util.*;

public class EfficientRater implements Rater {
    private String myID; //id of rater
    private HashMap<String, Rating> myRatings; //key is movieID, value is rating of movie

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String, Rating>();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item, new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        return myRatings.containsKey(item);
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        if(!myRatings.containsKey(item)){
            return -1;
        }
        return myRatings.get(item).getValue();
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(String s : myRatings.keySet()){
            list.add(s);
        }
        return list;
    }
}
