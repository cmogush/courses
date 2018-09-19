//returns true if the movie satisfies the criteria in the method and returns false otherwise
public interface Filter {
	public boolean satisfies(String id); //All filters that implement this interface must also have this method
}
