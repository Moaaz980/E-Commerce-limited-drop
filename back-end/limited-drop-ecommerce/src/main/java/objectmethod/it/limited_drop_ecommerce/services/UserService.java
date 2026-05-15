package objectmethod.it.limited_drop_ecommerce.services;

import objectmethod.it.limited_drop_ecommerce.dtos.request.AdminCreationRequestDto;
import objectmethod.it.limited_drop_ecommerce.dtos.request.LoginRequestDto;
import objectmethod.it.limited_drop_ecommerce.dtos.request.RegisterRequestDto;
import objectmethod.it.limited_drop_ecommerce.dtos.response.AdminCreationResponseDto;
import objectmethod.it.limited_drop_ecommerce.dtos.response.LoginResponseDto;
import objectmethod.it.limited_drop_ecommerce.dtos.response.RegistrationResponseDto;

public interface UserService {
    LoginResponseDto authUser(LoginRequestDto credentials);
    RegistrationResponseDto createUser(RegisterRequestDto userInformation);
    AdminCreationResponseDto createUserAdmin(AdminCreationRequestDto adminInformation);
}
