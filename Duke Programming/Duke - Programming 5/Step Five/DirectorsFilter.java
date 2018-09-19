//2018 Christopher Mogush
//DirectorsFilter.java

//selects only those movies with runtime greater than minMinutes but no more than maxMinutes
public class DirectorsFilter implements Filter {
    private String[] myDirectors;
    
    public DirectorsFilter(String directors) {
        myDirectors = directors.split("\\s*,"); //breaks string into array at commas
    }
    
    @Override
    public boolean satisfies(String id) {
        for(int k = 0; k < myDirectors.length; k++){
            if(MovieDatabase.getDirector(id).contains(myDirectors[k])){ return true;}
        }
        return false;
    }

}
