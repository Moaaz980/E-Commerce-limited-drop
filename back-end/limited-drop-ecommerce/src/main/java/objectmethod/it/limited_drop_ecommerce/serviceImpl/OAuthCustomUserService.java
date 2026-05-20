package objectmethod.it.limited_drop_ecommerce.serviceImpl;

import lombok.RequiredArgsConstructor;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import objectmethod.it.limited_drop_ecommerce.Constants;
import objectmethod.it.limited_drop_ecommerce.entities.User;
import objectmethod.it.limited_drop_ecommerce.enums.UserRole;
import objectmethod.it.limited_drop_ecommerce.repositories.UserRepository;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
@RequiredArgsConstructor
public class OAuthCustomUserService implements OAuth2UserService {
    UserRepository userRepository;
    DefaultOAuth2UserService defaultOAuth2UserService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = defaultOAuth2UserService.loadUser(userRequest);
        Map<String , Object> attributes = oAuth2User.getAttributes();
        List<GrantedAuthority> autorities = new ArrayList <>((List<GrantedAuthority>) oAuth2User.getAuthorities());
        String subject = (String) attributes.get("sub");
        Optional<User> utente = userRepository.findBySub(subject);
        CustomOAuth2User userAuth = new CustomOAuth2User();

        // Controllo se utente esiste aggiorno se no salvo
        if (utente.isPresent()) {
            User user = utente.get();
            user.setEmail((String) attributes.get("email"));
            user.setName(extractName(attributes));
            user.setSurname((String) attributes.get("family_name"));
            userAuth.setUser(user);
            userAuth.setAttributes(attributes);
            userAuth.setAuthorities(autorities);
            userAuth.setName(user.getEmail());
            userRepository.save(user);
            return userAuth;
        }
        else {
            // qui devo crrearlo e salvarlo
            User authUser = new User();
            authUser.setEmail((String) attributes.get("email"));
            // Mi restituisce il nome completo devo prendere solo il nome per salvarlo nel database
            authUser.setName(extractName(attributes));
            authUser.setSurname((String) attributes.get("family_name"));
            authUser.setPassword(Constants.OAUTHUSER2_USER);
            authUser.setRole(UserRole.USER);
            authUser.setSub(subject);
            userAuth.setUser(authUser);
            userAuth.setName(authUser.getEmail());
            userAuth.setAuthorities(autorities);
            userAuth.setAttributes(attributes);
            userRepository.save(authUser);
            return userAuth;
        }

    }

    private String extractName(Map<String , Object> map) {
        String extractedName = (String) map.get("name");
        if (extractedName == null) {
            String extractedGivenName = (String) map.get("given_name");
            if (extractedGivenName != null) {
                return extractedGivenName;
            }
            return "";
        }
        else {
            return extractedName.split(" " , 2)[0];
        }
    }

}
