package pl.shoeshop.shoeshop.security.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;

import static pl.shoeshop.shoeshop.security.jwt.Constants.AUTHORIZATION;
import static pl.shoeshop.shoeshop.security.jwt.Constants.BEARER;

@Component
public class JwtFilter extends GenericFilterBean {

    private TokenProvider tokenProvider;

    @Autowired
    public JwtFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String bearerToken = String.valueOf(httpServletRequest.getHeader(AUTHORIZATION));

            if (bearerToken.startsWith(BEARER)) {
                String token = bearerToken.replace(BEARER, "");
                Claims body = tokenProvider.resolveBody(token);

                if (isNotExpired(body)) {
                    String role = tokenProvider.getRole(body);

                    Authentication authentication = new UsernamePasswordAuthenticationToken(body.getSubject(), "", Collections.singletonList(new SimpleGrantedAuthority(role)));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    throw new ServletException("Token has expired");
                }
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isNotExpired(Claims body) {
        return Date.from(Instant.now()).before(body.getExpiration());
    }
}
