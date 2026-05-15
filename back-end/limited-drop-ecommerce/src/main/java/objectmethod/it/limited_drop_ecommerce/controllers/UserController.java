package objectmethod.it.limited_drop_ecommerce.controllers;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import objectmethod.it.limited_drop_ecommerce.dtos.request.AdminCreationRequestDto;
import objectmethod.it.limited_drop_ecommerce.dtos.request.LoginRequestDto;
import objectmethod.it.limited_drop_ecommerce.dtos.request.RegisterRequestDto;
import objectmethod.it.limited_drop_ecommerce.dtos.response.AdminCreationResponseDto;
import objectmethod.it.limited_drop_ecommerce.dtos.response.LoginResponseDto;
import objectmethod.it.limited_drop_ecommerce.dtos.response.RegistrationResponseDto;
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
    public ResponseEntity<RegistrationResponseDto> registerUser(@Valid @RequestBody RegisterRequestDto userInformation) {
        RegistrationResponseDto response = userService.createUser(userInformation);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/admin/users")
    public ResponseEntity< AdminCreationResponseDto> adminCreation(@Valid @RequestBody AdminCreationRequestDto adminInf) {
        AdminCreationResponseDto res = userService.createUserAdmin(adminInf);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

}
