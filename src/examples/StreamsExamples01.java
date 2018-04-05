package examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import examples.beans.Dish;
import examples.beans.Dish.Type;
import examples.beans.Student;

public class StreamsExamples01 {
	List<Dish> dish;
	
	public static void main(String[] args) {		
		Supplier<StreamsExamples01> rnd = StreamsExamples01::new;
		rnd.get().forEachExample();;
//		rnd.get().mapExample();
	}
	
	private void forEachExample() {
		List<String> titles = Arrays.asList("Java8", "In", "Action");
		String str = titles.stream()
								.collect(Collectors.joining(", "));
		System.out.print(str.toString());
//				.forEach(System.out::print); // [Java8 In Action]
		// map
		List<Integer> numbers = Arrays.asList(1,2,3,4);
		List<Integer> numbersRresult = numbers.stream()
											.map(StreamsExamples01 :: doubleIt)
											.collect(Collectors.toList());
		numbersRresult.forEach(System.out::print); // [2 4 6 8]
		// filter
		numbersRresult = numbers.stream()
								.filter(StreamsExamples01 :: isGT2)
								.collect(Collectors.toList());
		numbersRresult.forEach(System.out::print); // [3 4]
		// distinct values
		numbers = Arrays.asList(1,2,3,4,2,3,5,7);
		numbersRresult = numbers.stream()
								.distinct()
								.collect(Collectors.toList());
		numbersRresult.forEach(System.out::print); //[1 2 3 4 5 7]
	}

	private void flatMapExample() {
		// flatMap
		List<Student> students = populateStudents();
		List<String> collect = students.stream()
										.map(Student::getBooks) // Stream<Set<String>>
										.flatMap(x -> x.stream()) // Stream<String>
										.collect(Collectors.toList());
		collect.forEach(System.out::print);
		// Spring Boot in Action
		//Effective Java (2nd Edition)
		//Java 8 in Action
		//Learning Python, 5th Edition
	}

	
	private void mapExample() {
		List<Dish> dishes = populateDishes();
		// map
		List<String> result = dishes.stream()
									.map(Dish::getName)
									.collect(Collectors.toList());
		result.forEach(System.out::println); // apple  orange
		
		// filter
		List<String> resultDishes =  dishes.stream()
											.filter(Dish :: isVegetarian)
											.map(Dish :: getName)
											.collect(Collectors.toList());
		resultDishes.forEach(System.out::print); // [apple]
	}

	private static int doubleIt(int number) {
		return number * 2;
	}
	private static boolean isGT2(int number) {
		return number > 2;
	}


	private List<Dish> populateDishes() {
		List<Dish> dishes =  new ArrayList<>();
		dishes.add(new Dish("apple", true, 100, Type.OTHER));
		dishes.add(new Dish("orange", false, 100, Type.OTHER));
		return dishes;
	}
	
	private List<Student> populateStudents() {
		Student obj1 = new Student();
        obj1.setName("mkyong");
        obj1.addBook("Java 8 in Action");
        obj1.addBook("Spring Boot in Action");
        obj1.addBook("Effective Java (2nd Edition)");

        Student obj2 = new Student();
        obj2.setName("zilap");
        obj2.addBook("Learning Python, 5th Edition");
        obj2.addBook("Effective Java (2nd Edition)");

        List<Student> list = new ArrayList<>();
        list.add(obj1);
        list.add(obj2);
        
        return list;
	}

}
