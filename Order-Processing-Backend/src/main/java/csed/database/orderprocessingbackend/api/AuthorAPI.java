package csed.database.orderprocessingbackend.api;
import csed.database.orderprocessingbackend.model.Author;
import csed.database.orderprocessingbackend.model.Publisher;
import csed.database.orderprocessingbackend.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Author")
public class AuthorAPI {

    private final AuthorService authorService;

    public AuthorAPI(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/manager/addAuthor")
    public ResponseEntity<?> addAuthor(@RequestBody Author author){
        return new ResponseEntity<>(authorService.addAuthor(author));
    }

    @GetMapping("/getAllAuthor")
    public ResponseEntity<List<Author>> getAllAuthor(){
        List<Author> list = authorService.getAllAuthors();
        if (list == null){
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping("/manager/editAuthor")
    public ResponseEntity<?> editAuthor(@RequestBody Author author){
        return new ResponseEntity<>(authorService.editAuthor(author));
    }
    @DeleteMapping("/manager/deleteAuthor")
    public ResponseEntity<?> deleteAuthor(@RequestParam("author_id") Long author_id){
        return new ResponseEntity<>(authorService.deleteAuthor(author_id));
    }

    @GetMapping("/getAuthorByISBN")
    public ResponseEntity<List<Author>> getPublisherByISBN(@RequestParam Long ISBN){
        List<Author> a = authorService.getAuthorsByISBN(ISBN);
        if (a != null){
            return new ResponseEntity<>(a, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
    }
}
