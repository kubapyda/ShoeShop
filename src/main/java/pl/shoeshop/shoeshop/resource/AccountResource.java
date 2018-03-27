package pl.shoeshop.shoeshop.resource;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.shoeshop.shoeshop.dto.LoginDTO;

@RestController
@RequestMapping("accounts/")
public class AccountResource {

    @RequestMapping(value = "signIn", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> signIn(@RequestBody LoginDTO dto) {
        return ResponseEntity.ok()
                .header("Authorization", "?")
                .build();
    }
}
