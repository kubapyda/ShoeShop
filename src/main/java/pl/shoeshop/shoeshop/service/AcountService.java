package pl.shoeshop.shoeshop.service;

import pl.shoeshop.shoeshop.dto.LoginDTO;

public interface AcountService {
    void signIn(LoginDTO dto);
}
