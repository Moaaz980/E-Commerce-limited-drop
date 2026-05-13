package objectmethod.it.limited_drop_ecommerce.mappers;


import objectmethod.it.limited_drop_ecommerce.dtos.model.UserDto;
import objectmethod.it.limited_drop_ecommerce.entities.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper (componentModel = "spring")
public interface UserMapper {

    User toUser(UserDto userDto);
    UserDto toUserDto(User user);
    List<UserDto> toListUserDto(List <User> users);
    List<User> toListUser(List<UserDto> usersDto);
}
