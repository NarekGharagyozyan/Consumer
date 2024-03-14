package com.consumer.app.service.consumer;

import com.consumer.app.mapper.UserMapper;
import com.consumer.app.model.dto.user.UserRequestDto;
import com.consumer.app.model.entity.user.UserEntity;
import com.consumer.app.repository.UserRepository;
import com.consumer.app.service.user.UserServiceImpl;
import com.consumer.app.util.constants.Gender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class KafkaConsumerTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testRegistration_SkipWhenUsernameExists() {

        UserRequestDto userRequestDto = new UserRequestDto("Narek", null, "Gharagyozyan", 20, "existingUsername", "password", Gender.MALE);
        when(userRepository.existsByUsername(userRequestDto.getUsername())).thenReturn(true);

        userService.registration(userRequestDto);

        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @Test
    public void testRegistration_FailWhenThrowsExceptionDuringSaving() {
        UserRequestDto userRequestDto = new UserRequestDto("Narek", null, "Gharagyozyan", 20, "newUsername", "password", Gender.MALE);
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userMapper.userRequestDtoToEntity(any(UserRequestDto.class))).thenThrow(new RuntimeException("Something went wrong"));

        try {
            userService.registration(userRequestDto);
        } catch (Exception ignored) {
            //-----
        }

        verify(userRepository, never()).save(any(UserEntity.class));
    }
}