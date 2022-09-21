package by.shilay.app.consumer.kafka;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class KafkaMessage {

    private Payload payload;

    @Override
    public String toString() {
        return "KafkaMessage [payload= " + payload + " ]";
    }
}
