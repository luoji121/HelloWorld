package lambda.test;

import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaTest {
	public static void main(String[] args) {
		Arrays.asList( "a", "b", "d" ).forEach( e -> {
		    System.out.print( e );
		    System.out.print( e );
		} );
	
	
	Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> {
	    int result = e1.compareTo( e2 );
	    return result;
	} );
	
	new Consumer<String>() {

		@Override
		public void accept(String e) {
			// TODO Auto-generated method stub
			System.out.print( e );
		    System.out.print( e );
		}
	};
	
	Stream<String> ss = Arrays.asList( "a", "b", "b", "d" ).stream();
//	System.out.println(ss.collect(Collectors.toList()));
	System.out.println(ss.distinct().collect(Collectors.toList()));

	}
}
