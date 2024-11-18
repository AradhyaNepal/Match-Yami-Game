package com.a2.pickyami.practice;

import com.a2.pickyami.practice.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Controller
@Slf4j
@SpringBootApplication
public class RSocketServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RSocketServerApplication.class, args);
    }

    @MessageMapping("request-response")
    Mono<Message> requestResponse(final Message message) {
        log.info("Received request-response message: {}", message);
        return Mono.just(new Message("You said: " + message.getMessage()));
    }

    @MessageMapping("fire-and-forgot")
    Mono<Void> fireAndForget(final Message message) {
        log.info("Received fire-and-forgot request: {}", message);
        return Mono.empty();
    }

    @MessageMapping("request-stream")
    Flux<Message> stream(final Message message){
      return  Flux.interval(Duration.ofSeconds(1)).map(index->new Message("You said: "+message.getMessage()+index)).log();
    }

    @MessageMapping("stream-stream")
    Flux<Message> stream(final Flux<Integer> settings){
        return settings.doOnNext(setting->log.info("Requested interval is {} seconds.",setting))
                .doOnCancel(()->log.warn("The client cancelled the channel."))
                .switchMap(setting->Flux.interval(Duration.ofSeconds(setting))
                        .map(index->new Message("Stream Response #"+index)))
                .log();
       }
}
