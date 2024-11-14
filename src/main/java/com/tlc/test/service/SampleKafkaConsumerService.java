package com.tlc.test.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@Log4j2
@Service
@RequiredArgsConstructor
public class SampleKafkaConsumerService {
    
    @KafkaListener(topics = "${spring.kafka.topicName}", groupId = "${spring.kafka.consumer.group-id}")        
    public void consume(String message) throws IOException {
        log.info(String.format("Consumed message : %s", message));
    }
}
