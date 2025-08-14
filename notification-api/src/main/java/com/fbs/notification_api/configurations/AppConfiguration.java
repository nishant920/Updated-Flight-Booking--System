package com.fbs.notification_api.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.HashMap;
import java.util.Properties;

@Configuration
public class AppConfiguration {
    @Bean
    public HashMap<Integer, Integer> generateHashMap(){
        return new  HashMap<>();
    }
    @Bean
    public JavaMailSender generateJavaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost("smtp.gmail.com"); // For now email which i am using belongs to gmail so, the host will be smtp.gmail.com
        javaMailSender.setPort(587); // genrally to send mail from our computer we require some port number so, the port number which we will use is 587
        javaMailSender.setUsername("accioshoppingwebsite@gmail.com");// We will be sending email so, by what email our spring application will send mail to the users
        javaMailSender.setPassword("relcfdwhahhcvokv"); // Password of the email.... It is app password, not actual password
        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true"); // Our springboot api will connect gmail to send email via password so, mail.smtp.auth is true
        props.put("mail.smtp.starttls.enable", "true"); // This property we are setting for secure connection
        return javaMailSender;
    }
}
