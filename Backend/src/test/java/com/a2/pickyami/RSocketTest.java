package com.a2.pickyami;

import com.a2.pickyami.practice.model.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rsocket.server.LocalRSocketServerPort;

import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class RSocketTest {
    private static RSocketRequester requester;

    public static void setupOnce(@Autowired RSocketRequester.Builder builder, @LocalRSocketServerPort Integer port, @Autowired RSocketStrategies strategies) {
        requester = builder.connectTcp("localhost", port).block();
    }

    @Test
    public void testRequestGetsResponse() {
        Mono<Message> response = requester.route("request-response").data(new Message("TEST")).retrieveMono(Message.class);

        StepVerifier.create(response).consumeNextWith(message -> {
            assertThat(message.getMessage()).isEqualTo("You said: TEST");
        }).verifyComplete();
    }

    @Test
    public void testFireAndForget() {
        Mono<Void> result = requester.route("fire-and-forget").data(new Message("TEST")).retrieveMono(Void.class);

        StepVerifier.create(result).verifyComplete();
    }

    @Test
    public void testRequestStream() {
        Mono<Message> result = requester.route("request-stream").data(new Message("TEST")).retrieveMono(Message.class);

        StepVerifier.create(result).consumeNextWith(message->assertThat(message.getMessage()).isEqualTo("You said: Test.1")).expectNextCount(0)
                .consumeNextWith(message->{
                    assertThat(message.getMessage()).isEqualTo("You said: Test2");
                }).thenCancel().verify();
    }
}
