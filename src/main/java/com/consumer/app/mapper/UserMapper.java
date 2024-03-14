package com.consumer.app.mapper;

import com.consumer.app.model.dto.user.UserRequestDto;
import com.consumer.app.model.entity.user.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity userRequestDtoToEntity(UserRequestDto userRequestDto);

}