package objectmethod.it.limited_drop_ecommerce.serviceImpl;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import objectmethod.it.limited_drop_ecommerce.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import java.util.Collection;
import java.util.Map;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class CustomOAuth2User implements OAuth2User {
    Map<String , Object> attributes;
    Collection < ? extends GrantedAuthority > authorities;
    String name;
    User user;

    @Override
    public Map < String, Object > getAttributes() {
        return attributes;
    }

    @Override
    public Collection < ? extends GrantedAuthority > getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return name;
    }
}
