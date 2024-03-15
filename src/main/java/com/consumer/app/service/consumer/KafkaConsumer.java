package com.consumer.app.service.consumer;

import com.consumer.app.model.dto.user.UserRequestDto;
import com.consumer.app.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
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
        log.info("userRequestDto with username {} received.", userRequestDto.getUsername());
        userService.registration(userRequestDto);
    }

}