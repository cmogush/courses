//2017 Christopher Mogush
//PhraseFilter class

public class PhraseFilter implements Filter {
    //private instance variables
    private String phrase;
    private String where;
    private String filterName;
    //constructor to initialize instance variables
    public PhraseFilter(String p, String w, String name){
        phrase = p;
        where = w;
        filterName = name;
    }
    //satisfies method
    public boolean satisfies(QuakeEntry qe){
        if(where.equals("start")){ return qe.getInfo().startsWith(phrase); }
        if(where.equals("any")){ return qe.getInfo().indexOf(phrase) > -1; }
        if(where.equals("end")){ return qe.getInfo().endsWith(phrase); }
        return false;
    }
    public String getName(){
        return filterName;
    }
}
