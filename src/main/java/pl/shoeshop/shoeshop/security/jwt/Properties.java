package pl.shoeshop.shoeshop.security.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "jwt")
public class Properties {

    private String secret;

    private Long validity;
}