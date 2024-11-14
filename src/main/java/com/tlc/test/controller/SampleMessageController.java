package com.tlc.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.Callable;
import lombok.RequiredArgsConstructor;

import com.tlc.test.service.SampleMessageService;

@RestController
@RequiredArgsConstructor
public class SampleMessageController {
    
    private final SampleMessageService sampleMessageService;

    @GetMapping(value = "/send/message")
    public String publish() {
        return sampleMessageService.publish();
    }
}
