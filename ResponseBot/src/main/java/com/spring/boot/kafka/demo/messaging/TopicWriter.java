package com.spring.boot.kafka.demo.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TopicWriter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TopicWriter.class);
    
    String  REQUEST_TOPIC = "response-topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${jsa.kafka.topic}")
    String kafkaTopic = "response-topic";

    public void send(String data) {
        LOGGER.info("Writting to Topic ='{}'", data);
        kafkaTemplate.send(REQUEST_TOPIC, data);
    }
}
