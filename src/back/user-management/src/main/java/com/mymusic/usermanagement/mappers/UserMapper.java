package com.mymusic.usermanagement.mappers;

import com.mymusic.usermanagement.dto.AuthUserDto;
import com.mymusic.usermanagement.dto.PublicUserDto;
import com.mymusic.usermanagement.dto.UserPreviewDto;
import com.mymusic.usermanagement.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "name", source = "username")
    UserPreviewDto toUserPreviewDto(User user);
    @Mapping(target = "userId", source = "id")
    AuthUserDto toAuthUserDto(User user);
    PublicUserDto toPublicUserDto(User user);
}
