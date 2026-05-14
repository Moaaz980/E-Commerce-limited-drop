package objectmethod.it.limited_drop_ecommerce.security;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import objectmethod.it.limited_drop_ecommerce.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;


@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomDetails implements UserDetails {
    String email;
    String password;
    String id;
    List<GrantedAuthority> authorities;

    public CustomDetails(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.id = user.getId();
        this.authorities = List.of(new SimpleGrantedAuthority("ROLE_"+user.getRole()));
    }

    @Override
    public Collection < ? extends GrantedAuthority > getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getId() {
        return id;
    }
}
