package csed.database.orderprocessingbackend.api;

import csed.database.orderprocessingbackend.model.Book;
import csed.database.orderprocessingbackend.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookAPI {

    private final BookService bookService;

    public BookAPI(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/getBookByISBN")
    public ResponseEntity<Book> getBookByISBN(@RequestParam Long ISBN){
        Book book = bookService.getBookByISBN(ISBN);
        if (book == null){
            return new ResponseEntity<>(book, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> list = bookService.getAllBooks();
        if (list == null){
            return new ResponseEntity<>(list, HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/getBooksFromTo")
    public ResponseEntity<List<Book>> getBooksFromTo(@RequestParam int from, @RequestParam int to){
        List<Book> list = bookService.getBooksFromTo(from, to);
        if (list == null){
            return new ResponseEntity<>(list, HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/manager/addBook")
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.addBook(book));
    }

    @DeleteMapping("/manager/deleteBook")
    public ResponseEntity<?> deleteBook(@RequestParam Long ISBN) {
        return new ResponseEntity<>(bookService.deleteBook(ISBN));
    }

    @PutMapping("/manager/editBook")
    public ResponseEntity<?> editBook(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.editBook(book));
    }

    @GetMapping("/findBooksByAttribute")
    public ResponseEntity<List<Book>> findBooksByAttribute(@RequestParam String criteria, @RequestParam String searchInput){
        List<Book> list = bookService.findBooksByAttribute(criteria, searchInput);
        if (list == null){
            return new ResponseEntity<>(list, HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/findBooksByPublisherName")
    public ResponseEntity<List<Book>> findBooksByPublisherName(@RequestParam String name){
        List<Book> list = bookService.findBooksByPublisherName(name);
        if (list == null){
            return new ResponseEntity<>(list, HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/findBooksByAuthorName")
    public ResponseEntity<List<Book>> findBooksByAuthorName(@RequestParam String first_name, @RequestParam String last_name){
        List<Book> list = bookService.findBooksByAuthorName(first_name, last_name);
        if (list == null){
            return new ResponseEntity<>(list, HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
