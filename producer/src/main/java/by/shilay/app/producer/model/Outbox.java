package by.shilay.app.producer.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Entity
public class Outbox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String event;

    @Column(name = "event_id")
    private Long eventId;

    private String payload;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
