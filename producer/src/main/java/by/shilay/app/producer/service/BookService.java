package by.shilay.app.producer.service;

import by.shilay.app.producer.model.Book;
import by.shilay.app.producer.model.Outbox;
import by.shilay.app.producer.repository.BookRepository;
import by.shilay.app.producer.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final OutboxRepository outboxRepository;

    @Transactional
    public void save(Book book){
        Book savedBook = bookRepository.save(book);
        Outbox outbox = new Outbox();
        outbox.setEvent("book_created");
        outbox.setEventId(savedBook.getId());
        outbox.setPayload(savedBook.toString());
        outbox.setCreatedAt(LocalDateTime.now());
        log.info("Save object into outbox: {}", outbox);
        outboxRepository.save(outbox);
        outboxRepository.delete(outbox);
    }
}
