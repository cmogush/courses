//2017 Christopher Mogush
//DinstanceFilter

public class DistanceFilter implements Filter {
    //private instance variables
    private Location from;
    private double distMax;
    private String filterName;
    //constructor to initialize instance variables
    public DistanceFilter(double max, Location point, String name){
        from = point;
        distMax = max;
        filterName = name;
    }
    //satisfies method
    public boolean satisfies(QuakeEntry qe){
        return from.distanceTo(qe.getLocation()) <= distMax;
    }
    public String getName(){
        return filterName;
    }
}
