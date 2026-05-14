package objectmethod.it.limited_drop_ecommerce.serviceImpl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import objectmethod.it.limited_drop_ecommerce.dtos.model.UserDto;
import objectmethod.it.limited_drop_ecommerce.dtos.request.LoginRequestDto;
import objectmethod.it.limited_drop_ecommerce.dtos.response.LoginResponseDto;
import objectmethod.it.limited_drop_ecommerce.entities.User;
import objectmethod.it.limited_drop_ecommerce.exceptions.ApiException;
import objectmethod.it.limited_drop_ecommerce.mappers.UserMapper;
import objectmethod.it.limited_drop_ecommerce.repositories.UserRepository;
import objectmethod.it.limited_drop_ecommerce.security.CustomDetails;
import objectmethod.it.limited_drop_ecommerce.security.JwtService;
import objectmethod.it.limited_drop_ecommerce.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
@Slf4j
public class UserServiceImpl implements UserService {
    JwtService jwtService;
    AuthenticationManager authenticationManager;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    UserRepository userRepository;

    @Override
    public LoginResponseDto authUser(LoginRequestDto credentials)  {
        log.info("Tentativo di login per utente: {}" , credentials.getUsername());
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getUsername()
                            , credentials.getPassword()
                    )
            );
            log.debug("Login riuscito per utente {}" , credentials.getUsername());
            CustomDetails userDetails = (CustomDetails) authentication.getPrincipal();
            String token = jwtService.generateToken(userDetails);
            log.info("Token utente : {}" , token);
            return new LoginResponseDto(token);
        } catch (AuthenticationException e) {
            log.warn("Login fallito per utente: {}" , credentials.getUsername());
            throw new ApiException("Login failed", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public UserDto createUser(UserDto userInformation) {
        log.info("Utente {} si sta registrando" , userInformation.getEmail());

        Optional<User> user = userRepository.findByEmail(userInformation.getEmail());

        if (user.isEmpty()) {
            User utente = userMapper.toUser(userInformation);
            utente.setPassword(passwordEncoder.encode(utente.getPassword()));
            User createdUser = userRepository.save(utente);
            log.info("User : {} creato con successo" , createdUser.getEmail());
            return userMapper.toUserDto(createdUser);
        }
        else {
            throw new ApiException("User already exists" , HttpStatus.CONFLICT);
        }
    }


}
