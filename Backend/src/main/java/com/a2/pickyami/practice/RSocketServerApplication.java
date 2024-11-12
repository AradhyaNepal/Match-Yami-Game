package com.a2.pickyami.practice;

import com.a2.pickyami.practice.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
@Slf4j
@SpringBootApplication
public class RSocketServerApplication {

    Mono<Message> requestResponse(final Message message){
        log.info("Received request-response message: {}",message);
        return Mono.just(new Message("You said: "+message.getMessage()));
    }
}
