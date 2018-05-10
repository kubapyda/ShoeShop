package pl.shoeshop.shoeshop.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.shoeshop.shoeshop.dto.LoginDTO;
import pl.shoeshop.shoeshop.entity.Account;
import pl.shoeshop.shoeshop.security.jwt.TokenProvider;
import pl.shoeshop.shoeshop.service.AccountService;

import static pl.shoeshop.shoeshop.security.jwt.Constants.AUTHORIZATION;
import static pl.shoeshop.shoeshop.security.jwt.Constants.BEARER;

@RestController
@RequestMapping("accounts/")
public class AccountResource {

    private AccountService accountService;

    private TokenProvider tokenProvider;

    public AccountResource(AccountService accountService, TokenProvider tokenProvider) {
        this.accountService = accountService;
        this.tokenProvider = tokenProvider;
    }

    @RequestMapping(value = "signIn", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> signIn(@RequestBody LoginDTO dto) {
        Account account = accountService.signIn(dto);

        if (account != null) {
            String token = tokenProvider.createToken(account);

            return ResponseEntity.ok()
                    .header(AUTHORIZATION, BEARER + token)
                    .build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .build();
        }
    }
}
