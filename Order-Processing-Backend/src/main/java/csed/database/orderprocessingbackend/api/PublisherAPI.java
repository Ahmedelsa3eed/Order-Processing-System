package csed.database.orderprocessingbackend.api;

import csed.database.orderprocessingbackend.model.Publisher;
import csed.database.orderprocessingbackend.service.PublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Publisher")
public class PublisherAPI {

    private final PublisherService publisherService;

    public PublisherAPI(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping("/manager/addPublisher")
    public ResponseEntity<?> addPublisher(@RequestBody Publisher publisher){
        return new ResponseEntity<>(publisherService.addPublisher(publisher));
    }

    @GetMapping("/getAllPublisher")
    public ResponseEntity<List<Publisher>> getAllPublisher(){
        List<Publisher> list = publisherService.getAllPublishers();
        if (list == null){
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping("/manager/editPublisher")
    public ResponseEntity<?> editPublisher(@RequestBody Publisher publisher){
        return new ResponseEntity<>(publisherService.editPublisher(publisher));
    }
    @DeleteMapping("/manager/deletePublisher")
    public ResponseEntity<?> deletePublisher(@RequestParam String name){
        return new ResponseEntity<>(publisherService.deletePublisher(name));
    }

    @GetMapping("/getPublisherByISBN")
    public ResponseEntity<Publisher> getPublisherByISBN(@RequestParam Long ISBN){
        Publisher p = publisherService.getPublisherByISBN(ISBN);
        if (p != null){
            return new ResponseEntity<>(p, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
    }


}
