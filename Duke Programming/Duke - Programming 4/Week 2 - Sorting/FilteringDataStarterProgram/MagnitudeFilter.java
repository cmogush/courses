//2017 Christopher Mogush
//MagnitudeFilter class

public class MagnitudeFilter implements Filter {
    //private instance variables
    private double magMin;
    private double magMax;
    private String filterName;
    //constructor to initialize instance variables
    public MagnitudeFilter(double min, double max, String name){
        magMin = min;
        magMax = max;
        filterName = name;
    }
    //satisfies method
    public boolean satisfies(QuakeEntry qe){
        return qe.getMagnitude() >= magMin && qe.getMagnitude() <= magMax;
    }
    public String getName(){
        return filterName;
    }
}
