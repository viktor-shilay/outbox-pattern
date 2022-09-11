package by.shilay.app.producer.service.impl;

import by.shilay.app.producer.model.Event;
import by.shilay.app.producer.model.Outbox;
import by.shilay.app.producer.repository.EventRepository;
import by.shilay.app.producer.repository.OutboxRepository;
import by.shilay.app.producer.service.api.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final OutboxRepository outboxRepository;

    @Transactional
    @Override
    public void save(Event event) {
        eventRepository.save(event);
        Outbox outbox = new Outbox();
        outbox.setEvent(event.toString());
        outboxRepository.save(outbox);
        outboxRepository.delete(outbox);
    }
}
