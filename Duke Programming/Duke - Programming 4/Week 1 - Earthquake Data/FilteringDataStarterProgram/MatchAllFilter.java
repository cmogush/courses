//2017 Christopher Mogush
//MatchAllFilter

import java.util.*;
public class MatchAllFilter implements Filter {
    //instance variables
    private ArrayList<Filter> filters;
    //constructor
    public MatchAllFilter(){
        filters = new ArrayList<Filter>();
    }
    //void method
    public void addFilter(Filter f){
        filters.add(f);
    }
    //satisfies method
    public boolean satisfies(QuakeEntry qe){
        for(Filter f : filters){
            if(!f.satisfies(qe)){ return false;}
        }
        return true;
    }
    public String getName(){
        String names = "Filters used are: ";
        for(Filter f : filters){
            names = names + " " + f.getName();
        }
        return names;
    }
}
