package examples;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Test {
	
	/**
	 * Lambda syntax
	 *  (parameters) -> expression
	 * (parameters) -> { statements; }
	 */
	public Test() {
		
	}
	
	public Test(Integer weight) {		
	}
	
	public Test(String s, Integer i) {		
	}
	
	List<Integer> numbers1 = Arrays.asList(1, 2, 3);
	List<Integer> numbers2 = Arrays.asList(3, 4);
	List<String> lines = Arrays.asList("spring", "node", "mkyong");
	
	public  void filter_Method() {
		List<String> result = lines.stream()                // convert list to stream
									.filter(line -> !"mkyong".equals(line))     // we dont like mkyong
									.collect(Collectors.toList());              // collect the output and convert streams to a List

        result.forEach(System.out::println); 
        result.forEach(String::toUpperCase); 				// toUpperCase is method of String
	}

	public static void main(String... s) {
		Supplier<Test> c1 = Test::new;  // A constructor reference to the default Test()constructor.
		c1.get().filter_Method();		// Calling Supplier�s get method will produce a new Test.
		// OR
		Supplier<Test> c2 = () -> new Test(); // A constructor reference to the default Test()constructor.
		c2.get().filter_Method();			 // Calling Supplier�s get method will produce a new Test.
		
		Function<Integer, Test> c3 = Test::new;	// If you have a constructor with signature Test(Integer weight)
		c3.apply(10);
		// OR
		Function<Integer, Test> c4 = (weight) -> new Test(weight);
		Test t4 = c4.apply(110);
		
		BiFunction<String, Integer, Test> c5 = Test::new; // If you have a two-argument constructor
		c5.apply("test", 110);
		// OR
		BiFunction<String, Integer, Test> c6 = (colour, weight) -> new Test(colour, weight);
		Test t5 = c6.apply("red", 110);
	}
}
