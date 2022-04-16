package com.bookstore.bookstore.service.emailService.Impl;
import com.bookstore.bookstore.dto.customer.CustomerDTO;
import com.bookstore.bookstore.model.email.Email;
import com.bookstore.bookstore.service.emailService.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.*;

@Service
public class EmailServiceImpl implements IEmailService {


    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    SpringTemplateEngine templateEngine;

    // Lưu giữ và là nơi chuyển template
    @Override
    public Boolean create(List<CustomerDTO> customers) {
        for (CustomerDTO customer : customers) {
            try {
                Email email = new Email();
                email.setTo(customer.getEmail());
                email.setName(customer.getName());
                email.setSubject(email.getName() + " ơi? Cậu có nhớ tớ không?");

                Map<String, Object> props = new HashMap<>();
                props.put("name", customer.getName());
                props.put("email", customer.getEmail());
                email.setModel(props);
                this.sendMail(email, "emailTemplate");
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public void sendMail(Email email, String templateName) throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
        Context context = new Context();
        context.setVariables(email.getModel());
        String html = templateEngine.process(templateName, context);
        messageHelper.setTo(email.getTo());
        messageHelper.setSubject(email.getSubject());
        messageHelper.setText(html, true);
        emailSender.send(message);
    }


}
