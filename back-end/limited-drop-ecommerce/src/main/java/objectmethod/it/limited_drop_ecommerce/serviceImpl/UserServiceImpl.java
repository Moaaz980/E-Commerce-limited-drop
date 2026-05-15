package objectmethod.it.limited_drop_ecommerce.serviceImpl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import objectmethod.it.limited_drop_ecommerce.dtos.request.AdminCreationRequestDto;
import objectmethod.it.limited_drop_ecommerce.dtos.request.LoginRequestDto;
import objectmethod.it.limited_drop_ecommerce.dtos.request.RegisterRequestDto;
import objectmethod.it.limited_drop_ecommerce.dtos.response.AdminCreationResponseDto;
import objectmethod.it.limited_drop_ecommerce.dtos.response.LoginResponseDto;
import objectmethod.it.limited_drop_ecommerce.dtos.response.RegistrationResponseDto;
import objectmethod.it.limited_drop_ecommerce.entities.User;
import objectmethod.it.limited_drop_ecommerce.enums.UserRole;
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
    public RegistrationResponseDto createUser(RegisterRequestDto userInformation) {
        log.info("Utente {} si sta registrando", userInformation.getEmail());
        Optional < User > user = userRepository.findByEmail(userInformation.getEmail());
        // controllo se utente esiste già per evitare duplicati
        if (user.isPresent()) {
            log.info("Utente : {} esiste gia", userInformation.getEmail());
            throw new ApiException("User already exists", HttpStatus.CONFLICT);
        }
        // controllo se le password combaciano
        if (!userInformation.getPassword().equals(userInformation.getConfirmPassword())) {
            log.info("Le password non combaciano");
            throw new ApiException("Passwords do not match", HttpStatus.BAD_REQUEST);
        }
        User utente = userMapper.toUser(userInformation);
        utente.setPassword(passwordEncoder.encode(userInformation.getPassword()));
        utente.setRole(UserRole.USER);
        User createdUser = userRepository.save(utente);
        log.info("User : {} creato con successo", createdUser.getEmail());
        return userMapper.toRegistrationResponseDto(createdUser);
    }

    @Override
    public AdminCreationResponseDto createUserAdmin(AdminCreationRequestDto userInformation) {
        log.info("Admin crea un utenza admin", userInformation.getEmail());
        Optional<User> user = userRepository.findByEmail(userInformation.getEmail());
        // controllo se utente esiste già per evitare duplicati
        if (user.isPresent()) {
            log.info("admin : {} esiste gia", userInformation.getEmail());
            throw new ApiException("User already exists", HttpStatus.CONFLICT);
        }
        // controllo se le password combaciano
        if (!userInformation.getPassword().equals(userInformation.getConfirmPassword())) {
            log.info("Le password non combaciano");
            throw new ApiException("Passwords do not match", HttpStatus.BAD_REQUEST);
        }
        User utente = userMapper.toUser(userInformation);
        utente.setPassword(passwordEncoder.encode(userInformation.getPassword()));
        utente.setRole(UserRole.ADMIN);
        User createdUser = userRepository.save(utente);
        log.info("admin : {} creato con successo", createdUser.getEmail());
        return userMapper.toAdminCreationResponseDto(createdUser);
    }


}


