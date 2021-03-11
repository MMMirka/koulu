package MirkaM.Bookstore.web;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import MirkaM.Bookstore.domain.Book;
import MirkaM.Bookstore.domain.BookRepository;
import MirkaM.Bookstore.domain.CategoryRepository;



@Controller

public class BookController {
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	//Show booklist
	@RequestMapping(value={"/", "/booklist"})
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	
	//Add new book
	@RequestMapping(value="/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
		return "addbook";
	}
	
	//Save new book
	@RequestMapping(value="/save", method= RequestMethod.POST)
	public String save(Book book) {
		repository.save(book);
		return "redirect:booklist";
		
	}
	
	//Delete 

	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		repository.deleteById(bookId);
		
		return "redirect:../booklist";
	}
	
	//Edit
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String modifyBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", repository.findById(bookId));
		model.addAttribute("categories", crepository.findAll());
		return "modifybook";
	}
	
	// REST all books
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) repository.findAll();
    }    

	// REST book by id
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBooktRest(@PathVariable("id") Long bookId) {	
    	return repository.findById(bookId);
    } 
    
    //log in
    @RequestMapping(value="/login")
	public String login() {
		return "login";
	} 
	

}
