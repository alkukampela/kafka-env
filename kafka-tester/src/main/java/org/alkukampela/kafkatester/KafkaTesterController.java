package org.alkukampela.kafkatester;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class KafkaTesterController {

    private KafkaTemplate<String, String> kafkaTemplate;

    private List<String> messages = new CopyOnWriteArrayList<>();

    public KafkaTesterController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "${listener.topic}")
    public void listen(ConsumerRecord<String, String> cr) {
        messages.add(cr.value());
    }

    @GetMapping(value = "send/{msg}")
    public void send(@PathVariable String msg) {
        kafkaTemplate.sendDefault(msg);
    }

    @GetMapping("messages")
    public ResponseEntity<String> listMessages() {
        String result = messages.toString();
        messages.clear();
        return new ResponseEntity<>(result, OK);
    }
}
