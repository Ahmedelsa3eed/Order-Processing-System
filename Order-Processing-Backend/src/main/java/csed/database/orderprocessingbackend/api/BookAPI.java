package csed.database.orderprocessingbackend.api;

import csed.database.orderprocessingbackend.model.Book;
import csed.database.orderprocessingbackend.service.BookService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookAPI {

    private final BookService bookService;

    public BookAPI(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/manager/addBook")
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.addBook(book));
    }

    @DeleteMapping("/manager/deleteBook")
    public ResponseEntity<?> deleteBook(@RequestParam Long bookISBN) {
        return new ResponseEntity<>(HttpStatusCode.valueOf(bookService.deleteBook(bookISBN)));
    }

    @PutMapping("/manager/editBook")
    public ResponseEntity<?> editBook(@RequestBody Book book) {
        return new ResponseEntity<>(HttpStatusCode.valueOf(bookService.editBook(book)));
    }

}
