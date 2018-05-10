package pl.shoeshop.shoeshop.service;

import pl.shoeshop.shoeshop.dto.LoginDTO;
import pl.shoeshop.shoeshop.entity.Account;

public interface AccountService {
    Account signIn(LoginDTO dto);
}
