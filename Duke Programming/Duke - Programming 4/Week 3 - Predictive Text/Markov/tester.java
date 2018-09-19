//2017 Christopher Mogush
//MarkovRunner

import edu.duke.*;
import java.util.*;

public class tester {
    public void testGetFollowsWithFile() { //generates three sets of randomly generated text using the file read in to choose the random characters from
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' '); //replaces all new lines with spaces
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st); //sets the training text to the st file
        ArrayList<String> list = markov.getFollows("he");
        System.out.println(list.size());
        System.out.println(list);
    }
    public void test(){
        for(int k = 0; k < 10; k++){
            System.out.println("first pass: " + k);
        }
    }
}
