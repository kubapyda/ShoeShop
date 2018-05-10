package pl.shoeshop.shoeshop.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.shoeshop.shoeshop.entity.Account;

import java.util.Date;

@Component
public class TokenProvider {

    private final static String ROLE = "role";
    private final static String ROLE_PREFIX = "ROLE_";

    private Properties properties;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public TokenProvider(Properties properties, PasswordEncoder passwordEncoder) {
        this.properties = properties;
        this.passwordEncoder = passwordEncoder;
    }

    public String createToken(Account account) {

        Date expireDate = new Date(new Date().getTime() + properties.getValidity());

        return Jwts.builder()
                .setExpiration(expireDate)
                .setSubject(account.getLogin())
                .claim(ROLE, account.getRole())
                .signWith(SignatureAlgorithm.HS256, properties.getSecret())
                .compact();
    }

    public Claims resolveBody(String token) {
        return Jwts.parser()
                .setSigningKey(properties.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }

    String getRole(Claims body) {
        return ROLE_PREFIX + body.get(ROLE, String.class);
    }
}
