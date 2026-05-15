package objectmethod.it.limited_drop_ecommerce.mappers;


import objectmethod.it.limited_drop_ecommerce.dtos.model.UserDto;
import objectmethod.it.limited_drop_ecommerce.dtos.request.AdminCreationRequestDto;
import objectmethod.it.limited_drop_ecommerce.dtos.request.RegisterRequestDto;
import objectmethod.it.limited_drop_ecommerce.dtos.response.AdminCreationResponseDto;
import objectmethod.it.limited_drop_ecommerce.dtos.response.RegistrationResponseDto;
import objectmethod.it.limited_drop_ecommerce.entities.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper (componentModel = "spring" , uses = {OrderMapper.class , TrolleyMapper.class})
public interface UserMapper {

    User toUser(UserDto userDto);
    UserDto toUserDto(User user);
    List<UserDto> toListUserDto(List <User> users);
    List<User> toListUser(List<UserDto> usersDto);
    User toUser(RegisterRequestDto registerRequestDto);
    RegistrationResponseDto toRegistrationResponseDto(User user);
    User toUser(AdminCreationRequestDto user);
    AdminCreationResponseDto toAdminCreationResponseDto(User user);
}
