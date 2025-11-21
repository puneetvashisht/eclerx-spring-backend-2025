package com.pv.rest_api_mongodb_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class KafkaMessageConsumer {

    @Autowired
    private ActivityRepository activityRepository;

    private static final Logger log = LoggerFactory.getLogger(KafkaMessageConsumer.class);

    @KafkaListener(topics = "quickstart-events", id = "fooGroup")
    public void listen(Activity message) {
        log.info("Received message: {}", message);
        // Process the received message here
        activityRepository.save(message);
    }

    // You can also access metadata like partition and offset
    @KafkaListener(topics = "another-topic", groupId = "my-consumer-group")
    public void listenWithMetadata(String message,
                                   @org.springframework.messaging.handler.annotation.Header(org.springframework.kafka.support.KafkaHeaders.RECEIVED_PARTITION) int partition,
                                   @org.springframework.messaging.handler.annotation.Header(org.springframework.kafka.support.KafkaHeaders.OFFSET) long offset) {
        log.info("Received message: {} from partition {} with offset {}", message, partition, offset);
    }
}

