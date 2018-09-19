//2017 Christopher Mogush
//DepthFilter

public class DepthFilter implements Filter {
    //private instance variables
    private double depthMin;
    private double depthMax;
    private String filterName;
    //constructor to initialize instance variables
    public DepthFilter(double min, double max, String name){
        depthMin = min;
        depthMax = max;
        filterName = name;
    }
    //satisfies method
    public boolean satisfies(QuakeEntry qe){
        return qe.getDepth() >= depthMin && qe.getDepth() <= depthMax;
    }
    public String getName(){
        return filterName;
    }
}
