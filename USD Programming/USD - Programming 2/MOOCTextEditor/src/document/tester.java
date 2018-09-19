package document;
import java.util.List;

public class tester {
	
	public void test() {
		BasicDocument test = new BasicDocument("this is a test");
		int num = test.getNumSyllables();
		System.out.println(num);
	}
}
