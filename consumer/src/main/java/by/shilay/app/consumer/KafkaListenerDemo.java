package by.shilay.app.consumer;

import by.shilay.app.consumer.kafka.Event;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListenerDemo {

    @KafkaListener(topics = "eventsTopic", groupId = "consumerId")
    public void listener(Event event){
        System.out.println("Listener received: " + event);
    }
}
