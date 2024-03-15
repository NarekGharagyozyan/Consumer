package com.consumer.app.service.user;

import com.consumer.app.mapper.UserMapper;
import com.consumer.app.model.dto.user.UserRequestDto;
import com.consumer.app.model.entity.user.UserEntity;
import com.consumer.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public void registration(UserRequestDto userRequestDto) {

        if (!userRepository.existsByUsername(userRequestDto.getUsername())) {
            log.info("Checking for existence of userRequestDto in database.");
            UserEntity userEntity = userMapper.userRequestDtoToEntity(userRequestDto);
            userRepository.save(userEntity);
            log.info("User is saved in database.");
        }
        else
            log.info("User with {} username already exists.",userRequestDto.getUsername()) ;
    }
}