//2017 Christopher Mogush
//This class implements Filter class
//

public class MinMagFilter implements Filter
{
    private double magMin; //constructor to initialize magMin
    private String filterName;
    
    public MinMagFilter(double min, String name) { 
        magMin = min;
        filterName = name;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        return qe.getMagnitude() >= magMin; //satisfies method, which is required
    } 
    public String getName(){
        return filterName;
    }
}
