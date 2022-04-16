package com.bookstore.bookstore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

import java.util.Properties;

@Configuration
public class SendingMail {

//    public static final String MY_EMAIL ="01207685754z@gmail.com";
//    public static final String APP_PASS ="ilmywlfisqqzoehh";
//
//    @Value("${mailServer.host}")
//    private String host;
//
// @Value("${mailServer.port}")
//    private Integer port;
//
// @Value("${mailServer.username}")
//    private String username;
//
// @Value("${mailServer.password}")
//    private String password;
//
// @Value("${mailServer.isSSL}")
//    private String isSSL;



    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("thiennd@dipro.vn");
        mailSender.setPassword("Thien1532000");
        mailSender.setDefaultEncoding("UTF-8");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.from","thiennd@dipro.vn");
        props.put("mail.debug", "true");

        return mailSender;
    }


}