package by.shilay.app.producer.controller;

import by.shilay.app.producer.model.Book;
import by.shilay.app.producer.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/books")
@RequiredArgsConstructor
@RestController
public class BookController {

    private final BookService bookService;

    @PostMapping
    public void save(@RequestBody Book book){
        bookService.save(book);
    }
}
