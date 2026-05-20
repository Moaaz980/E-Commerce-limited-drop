package objectmethod.it.limited_drop_ecommerce.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.persistence.GenerationType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import objectmethod.it.limited_drop_ecommerce.Constants;
import objectmethod.it.limited_drop_ecommerce.serviceImpl.CustomOAuth2User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class JwtService {

    Key key = Keys.hmacShaKeyFor(Constants.JWT_SECRET.getBytes(StandardCharsets.UTF_8));

    public String generateToken(CustomDetails user) {
        return Jwts
                .builder()
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + Constants.EXPIRATION))
                .setIssuedAt(new Date())
                .setId(user.getId())
                .claim(Constants.ROLE , user.getAuthorities())
                .signWith(key , SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(CustomOAuth2User customOAuth2User) {
        return Jwts
                .builder()
                .setSubject(customOAuth2User.getUser().getEmail())
                .setExpiration(new Date(System.currentTimeMillis() +  Constants.EXPIRATION))
                .setId(customOAuth2User.getUser().getId())
                .claim(Constants.ROLE , customOAuth2User.getUser().getRole())
                .signWith(key , SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsernameFromToken(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Date getExpirationDateFromToken(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }

    public List<GrantedAuthority> getAuthoritiesFromToken(String token) {
        List<GrantedAuthority> authorities = Collections.singletonList((GrantedAuthority) List.of(Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get(Constants.ROLE)
        ));
        return authorities;
    }

    public boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }

    public boolean validateToken(String token , CustomDetails userDetails)  {
        try {
            Claims claim = Jwts
                    .parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            boolean isExpired = claim.getExpiration().before(new Date());
            boolean hasTheRightSubject = userDetails.getUsername().equals(claim.getSubject());

            return !isExpired && hasTheRightSubject;
        }
        catch (JwtException e) {
            return false;
        }
    }

}
