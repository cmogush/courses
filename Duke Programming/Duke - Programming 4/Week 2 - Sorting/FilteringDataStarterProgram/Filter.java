//2017 Christopher Mogush
//filter interface
//any class which implements filter MUST provide "satisfies" method

public interface Filter
{
    public String getName();
    public  boolean satisfies(QuakeEntry qe); //any class which implements filter MUST provide satisfies method
}
