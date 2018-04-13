package guava.test;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

public class PermutationTest {
	public static void orderedPermutations() {

	    List<Integer> vals = Lists.newArrayList(1, 2, 3);

	    Collection<List<Integer>> orderPerm = Collections2.orderedPermutations(vals);

	    for (List<Integer> val : orderPerm) {
	        System.out.println(val);
	    }
	}
	
	public static void main(String[] args) {
		
		PermutationTest.orderedPermutations();
	}

}
