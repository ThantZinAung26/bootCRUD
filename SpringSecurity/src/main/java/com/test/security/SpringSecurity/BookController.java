package com.test.security.SpringSecurity;

import com.test.security.SpringSecurity.error.BookNotFoundException;
import com.test.security.SpringSecurity.error.BookUnSupportFieldPatchException;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;

@RestController
@Validated
public class BookController {

    private final BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/books")
    List<Book> findAll() {
        return repository.findAll();
    }

    @PostMapping("/books")
    @ResponseStatus
    Book newBook(@Valid @RequestBody Book newBook) {
        return repository.save(newBook);
    }

    @GetMapping("/books/{id}")
    Book findOne(@PathVariable @Min(1) Long id) {
        return repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    @PutMapping("/books/{id}")
    Book saveOrUpdate(@RequestBody Book book, @PathVariable Long id) {
        return repository.findById(id).map(b -> {
            b.setName(book.getName());
            b.setAuthor(book.getAuthor());
            b.setPrice(book.getPrice());
            return repository.save(b);
        }).orElseGet(() -> {
            book.setId(id);
            return repository.save(book);
        });
    }

    @PatchMapping("/books/{id}")
    Book patch(@RequestBody Map<String, String> update, @PathVariable Long id) {
        return repository.findById(id).map(b -> {
            String author = update.get("author");
            if (!StringUtils.isEmpty(author)) {
                b.setAuthor(author);
                return repository.save(b);
            } else {
                throw new BookUnSupportFieldPatchException(update.keySet());
            }
        }).orElseGet(() -> {
            throw new BookNotFoundException(id);
        });
    }

    @DeleteMapping("/books/{id}")
    void deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
