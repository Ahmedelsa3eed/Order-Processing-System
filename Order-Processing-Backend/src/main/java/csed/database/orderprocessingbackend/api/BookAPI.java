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

    @GetMapping("/getAllBooks")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> list = bookService.getAllBooks();
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

}
