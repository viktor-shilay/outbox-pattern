package by.shilay.app.consumer.kafka;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Payload {

    private int id;
    private String event;

    @JsonProperty("event_id")
    private int eventId;
    private String payload;

    @JsonProperty("created_at")
    private String createdAt;

}
