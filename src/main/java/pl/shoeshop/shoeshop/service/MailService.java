package pl.shoeshop.shoeshop.service;

import pl.shoeshop.shoeshop.entity.Order;

import javax.mail.MessagingException;

public interface MailService {

    void sendConfirmation(Order order) throws MessagingException;
}
