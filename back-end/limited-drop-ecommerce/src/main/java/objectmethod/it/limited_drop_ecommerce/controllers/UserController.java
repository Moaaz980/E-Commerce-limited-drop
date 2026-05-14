package objectmethod.it.limited_drop_ecommerce.controllers;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import objectmethod.it.limited_drop_ecommerce.dtos.model.UserDto;
import objectmethod.it.limited_drop_ecommerce.dtos.request.LoginRequestDto;
import objectmethod.it.limited_drop_ecommerce.dtos.response.LoginResponseDto;
import objectmethod.it.limited_drop_ecommerce.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@FieldDefaults(makeFinal = true , level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserController {
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody LoginRequestDto credentials) {
        LoginResponseDto response = userService.authUser(credentials);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userInformation) {
        UserDto createdUser = userService.createUser(userInformation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

}
