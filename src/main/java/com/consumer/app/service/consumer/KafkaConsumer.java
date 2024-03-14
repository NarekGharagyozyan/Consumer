package com.consumer.app.service.consumer;

import com.consumer.app.model.dto.user.UserRequestDto;
import com.consumer.app.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final UserService userService;

    @KafkaListener(
            topics = "userTopic",
            groupId = "user",
            containerFactory = "kafkaListenerContainerFactory",
            autoStartup = "true")
    public void receive(@Payload(required = false) UserRequestDto userRequestDto) {
        userService.registration(userRequestDto);
    }

}