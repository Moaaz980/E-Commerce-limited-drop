package objectmethod.it.limited_drop_ecommerce.services;


import objectmethod.it.limited_drop_ecommerce.dtos.model.UserDto;
import objectmethod.it.limited_drop_ecommerce.dtos.request.LoginRequestDto;
import objectmethod.it.limited_drop_ecommerce.dtos.response.LoginResponseDto;

public interface UserService {

    LoginResponseDto authUser(LoginRequestDto credentials);
    UserDto createUser(UserDto userInformation);
}
