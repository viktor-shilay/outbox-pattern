package by.shilay.app.consumer;

import by.shilay.app.consumer.kafka.KafkaMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaListenerDemo {

    @KafkaListener(topics = "events_db.public.outbox", groupId = "consumerId")
    public void listener(KafkaMessage kafkaMessage){
        log.info("Listener received a {}", kafkaMessage);
    }
}
