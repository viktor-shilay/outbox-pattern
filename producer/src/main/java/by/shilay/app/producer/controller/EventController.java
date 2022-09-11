package by.shilay.app.producer.controller;

import by.shilay.app.producer.model.Event;
import by.shilay.app.producer.service.api.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/event")
@RequiredArgsConstructor
@RestController
public class EventController {

    private final EventService eventService;

    @PostMapping
    public void saveEvent(@RequestBody Event event){
        eventService.save(event);
    }
}
