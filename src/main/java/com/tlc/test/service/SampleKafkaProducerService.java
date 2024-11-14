package com.tlc.test.service;

import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import com.tlc.test.response.ResponseObject;
import lombok.extern.log4j.Log4j2;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class SampleKafkaProducerService {
    
    @Value("${spring.kafka.topicName}")
    private String topicName;
    
    private final KafkaTemplate<String, String> kafkaTemplate;


    public ResponseObject sendMessage(String content) {
        log.info(String.format("sendMessage topic : %s, message : %s ", topicName, content));
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, content);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Sent message=[" + content +
                "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to send message=["
                + content + "] due to : " + ex.getMessage());
            }
        });

        ResponseObject responseObject = new ResponseObject();
        responseObject.putResult(content);
        return responseObject;
    }
}
