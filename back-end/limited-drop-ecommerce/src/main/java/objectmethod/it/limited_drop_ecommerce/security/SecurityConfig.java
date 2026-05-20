package objectmethod.it.limited_drop_ecommerce.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import objectmethod.it.limited_drop_ecommerce.dtos.response.GoogleAuthenticationResponseDto;
import objectmethod.it.limited_drop_ecommerce.exceptions.ApiException;
import objectmethod.it.limited_drop_ecommerce.serviceImpl.CustomOAuth2User;
import objectmethod.it.limited_drop_ecommerce.serviceImpl.OAuthCustomUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.AccessLevel;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
@Slf4j
public class SecurityConfig {
    AuthenticationFilter authFilter;
    OAuthCustomUserService userService;
    JwtService jwtService;
    ObjectMapper objMapper;
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        http
                . csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(req -> req
                        .requestMatchers(HttpMethod.POST , "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST , "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST , "/auth/admin/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET , "/auth/users").permitAll()
                        .requestMatchers( "/oauth2/authorization/**"
                                ).permitAll()
                        .requestMatchers( "/login/oauth2/code/**").permitAll()
                        .requestMatchers(HttpMethod.GET , "/products").hasRole("USER")
                        .requestMatchers(HttpMethod.GET , "/products/{id}").hasRole("USER")
                        .anyRequest().authenticated()
                )
                    .oauth2Login(oauth2 ->oauth2
                            .userInfoEndpoint(userInfo -> userInfo.userService(userService))
                            .successHandler((req , res , auth) -> {
                                    // Qui devo generare un nuovo token per utente authenticato con google
                                    if (auth.getPrincipal() instanceof CustomOAuth2User) {
                                        CustomOAuth2User userDetails = (CustomOAuth2User) auth.getPrincipal();
                                        log.debug("User fornito da google: {}" , userDetails.getName());
                                        String token = jwtService.generateToken(userDetails);
                                        GoogleAuthenticationResponseDto response = new GoogleAuthenticationResponseDto(token);
                                        res.setContentType("application/json");
                                        String jsonObj = objMapper.writeValueAsString(response);
                                        res.setStatus(HttpServletResponse.SC_OK);
                                        log.debug("Token di risposta : {}" , jsonObj);
                                        res.getWriter().write(jsonObj);
                                    }
                                    else {
                                        throw new ApiException("Login with google failed" , HttpStatus.BAD_REQUEST);
                                    }
                            })
                    )
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .addFilterBefore(authFilter , UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    // Authentication manager per la gestione dell'autenticazione utente
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // Password encoder per cifrare le password utente
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configurazione cors
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET" , "POST" , "PUT" , "DELETE" , "OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**" , corsConfiguration);
        return source;
    }

}
