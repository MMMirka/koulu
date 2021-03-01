package MirkaM.Bookstore;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import MirkaM.Bookstore.domain.Book;
import MirkaM.Bookstore.domain.BookRepository;
import MirkaM.Bookstore.domain.Category;
import MirkaM.Bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository crepository) {
		return (args) -> {
			
		crepository.save(new Category("Fantacy"));
		crepository.save(new Category("School"));
		crepository.save(new Category("Drama"));
			
		Book kirja1 = new Book ("Harry Potter", "J.K.Rowling","1997","9789520401801","20", crepository.findByName("Fantacy").get(0));
		
		repository.save(kirja1);

		};
	}
}
