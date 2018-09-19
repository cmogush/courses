//2018 Christopher Mogush
//MinutesFilter.java

//selects only those movies with runtime greater than minMinutes but no more than maxMinutes
public class MinutesFilter implements Filter {
    private int myMin;
    private int myMax;
    
    public MinutesFilter(int minMinutes, int maxMinutes) {
        myMin = minMinutes;
        myMax = maxMinutes;
    }
    
    @Override
    public boolean satisfies(String id) {
        if(MovieDatabase.getMinutes(id) >= myMin 
            && MovieDatabase.getMinutes(id) <= myMax){
                return true;
        }
        return false;
    }

}
