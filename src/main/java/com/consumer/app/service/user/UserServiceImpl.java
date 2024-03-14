package com.consumer.app.service.user;

import com.consumer.app.mapper.UserMapper;
import com.consumer.app.model.dto.user.UserRequestDto;
import com.consumer.app.model.entity.user.UserEntity;
import com.consumer.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public void registration(UserRequestDto userRequestDto) {

        if (!userRepository.existsByUsername(userRequestDto.getUsername())) {
            UserEntity userEntity = userMapper.userRequestDtoToEntity(userRequestDto);
            userRepository.save(userEntity);
        }

    }
}