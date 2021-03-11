package MirkaM.Bookstore;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import MirkaM.Bookstore.domain.Book;
import MirkaM.Bookstore.domain.BookRepository;
import MirkaM.Bookstore.domain.Category;
import MirkaM.Bookstore.domain.CategoryRepository;
import MirkaM.Bookstore.domain.User;
import MirkaM.Bookstore.domain.UserRepository;


@SpringBootApplication
public class BookstoreApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			
		crepository.save(new Category("Fantacy"));
		crepository.save(new Category("School"));
		crepository.save(new Category("Drama"));
			
		Book kirja1 = new Book ("Harry Potter", "J.K.Rowling","1997","9789520401801","20", crepository.findByName("Fantacy").get(0));
		
		repository.save(kirja1);
		
		// Create users
		User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
		User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
		urepository.save(user1);
		urepository.save(user2);

		};
	}
}
