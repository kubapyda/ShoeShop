package pl.shoeshop.shoeshop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.shoeshop.shoeshop.security.jwt.JwtFilter;
import pl.shoeshop.shoeshop.security.jwt.Properties;
import pl.shoeshop.shoeshop.type.RoleType;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private JwtFilter filter;

    @Lazy
    @Autowired
    public SecurityConfiguration(JwtFilter filter) {
        this.filter = filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable()
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/shoes/add", "/shoes/edit", "/shoes/delete/*").hasRole(RoleType.ADMIN.name())
                .antMatchers("/orders/find/**", "/orders/changeStatus/**").hasRole(RoleType.ADMIN.name())
                .antMatchers(HttpMethod.POST, "/shoes/*/picture").hasRole(RoleType.ADMIN.name());
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(
                                "http://localhost:4200",
                                "http://ng-shoeshop.s3-website.eu-central-1.amazonaws.com",
                                "http://ng-shoeshop2.s3-website.us-east-2.amazonaws.com"
                        );
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Properties jwtProperties() {
        return new Properties();
    }
}
