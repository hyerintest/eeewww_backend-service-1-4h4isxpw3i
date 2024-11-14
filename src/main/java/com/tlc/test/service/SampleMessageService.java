package com.tlc.test.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import com.tlc.test.model.CustomEventMessage;

@Service
@RequiredArgsConstructor
public class SampleMessageService {

    private static final String queueName = "test_queue";
    private final RabbitTemplate rabbitTemplate;

    public String publish() {
        CustomEventMessage message = CustomEventMessage.builder().payload("Sample Message!").build();
        rabbitTemplate.convertAndSend(queueName,  message);
        return "success";
    }
}
