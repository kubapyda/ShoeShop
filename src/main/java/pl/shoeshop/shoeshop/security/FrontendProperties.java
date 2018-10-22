package pl.shoeshop.shoeshop.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "frontend")
public class FrontendProperties {

    private String url;
}