package pl.shoeshop.shoeshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.shoeshop.shoeshop.dto.LoginDTO;
import pl.shoeshop.shoeshop.entity.Account;
import pl.shoeshop.shoeshop.repository.AccountRepository;
import pl.shoeshop.shoeshop.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Account signIn(LoginDTO dto) {
        Account account = accountRepository.findByLogin(dto.getLogin());

        boolean authenticated = account != null && passwordEncoder.matches(dto.getPassword(), account.getPassword());

        return authenticated ? account : null;
    }
}
