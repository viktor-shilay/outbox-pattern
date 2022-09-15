package by.shilay.app.producer.service;

import by.shilay.app.producer.model.Event;
import by.shilay.app.producer.model.Outbox;
import by.shilay.app.producer.repository.EventRepository;
import by.shilay.app.producer.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class EventService {

    private final EventRepository eventRepository;
    private final OutboxRepository outboxRepository;
    private final KafkaTemplate<String, Event> kafkaTemplate;

    @Transactional
    public void save(Event event) {
        Event savedEvent = eventRepository.save(event);

        kafkaTemplate.send("eventsTopic", savedEvent);

        Outbox outbox = new Outbox();
        outbox.setEvent(event.toString());
        outboxRepository.save(outbox);
        outboxRepository.delete(outbox);
    }
}
