package com.github.hels.tradeplatform.onboarding.mappers;

import com.github.hels.tradeplatform.onboarding.dto.CreateUserDto;
import com.github.hels.tradeplatform.onboarding.dto.domain.UserDto;
import com.github.hels.tradeplatform.onboarding.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    default CreateUserDto.Request toDto(User entity) {
        if (entity == null)
            return null;
        CreateUserDto.Request dto = new CreateUserDto.Request();
        dto.setName(entity.getName());
        dto.setDocument(entity.getDocument());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setPhoneNumber(entity.getPhoneNumber());
        return dto;
    }

    UserDto toUserDto(User entity);

    UserDto toUserDto(CreateUserDto.Request request);

    User toUser(UserDto userDto);
}
