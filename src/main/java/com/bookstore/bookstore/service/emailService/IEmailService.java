package com.bookstore.bookstore.service.emailService;

import com.bookstore.bookstore.dto.customer.CustomerDTO;
import com.bookstore.bookstore.model.email.Email;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface IEmailService {
    void sendMail(Email email, String templateName)
            throws MessagingException, IOException;
    Boolean create(List<CustomerDTO> customerDTOSet);
}
