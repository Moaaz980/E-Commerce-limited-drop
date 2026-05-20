package objectmethod.it.limited_drop_ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;

@Configuration
public class BeanConfiguration {

    @Bean
    public DefaultOAuth2UserService defaultUser() {
        return new DefaultOAuth2UserService();
    }
}
