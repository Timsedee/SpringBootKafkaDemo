package com.spring.boot.kafka.demo.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.spring.boot.kafka.demo.storage.MessageStorage;

@Component
public class TopicReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(TopicReader.class);

    @Autowired
    MessageStorage storage;
    
    @Autowired
    Processor processor;
    
    @KafkaListener(topics = "${jsa.kafka.topic}")
    public void processMessage(String content) {
        LOGGER.info("Reading Data from Topic = '{}'", content);
        
        processor.sendToResponseQueue(content);
        //storage.put(content);
    }
}
