package examples;

import examples.beans.Author;
import examples.beans.Book;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

public class StreamsExamples02 {

    public static void main(String... s) {
        Set<Book> books = getBooks();
        List<Author> authors = getAuthors();


//        https://www.mkyong.com/java8/java-8-flatmap-example/
//        Stream<String[]>		-> flatMap ->	Stream<String>
//        Stream<Set<String>>	-> flatMap ->	Stream<String>
//        Stream<List<String>>	-> flatMap ->	Stream<String>
//        Stream<List<Object>>	-> flatMap ->	Stream<Object>
        Set<Book> r2 = authors.stream()
                                .map(Author::getBooks) // Set<Set<Book>>
                                .flatMap(l -> l.stream())
                                .collect(Collectors.toSet());
         System.out.println(r2);

    }

    private static Set<Book>  getBooks(){
        Set<Book> books = new HashSet<>();
        Author auth = new Author("Nikos", "Samaras", books);

        Book book = new Book("title", auth);
        books.add(book);

        return books;
    }

    private static List<Author> getAuthors(){
        List<Author> authors = new ArrayList<>();
        Set<Book> books = new HashSet<>();
        Author auth = new Author("Nikos", "Samaras", books);
        Book book = new Book("title", auth);
        books.add(book);
        authors.add(auth);
        return authors;
    }

}
