package com.spo.core_app.configurations;


import io.imagekit.sdk.ImageKit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;

@Configuration
public class SystemConfiguration {

    @Value("${imagekit.public.key}")
    private String imageKitPublicKey;
    @Value("${imagekit.private.key}")
    private String imageKitPrivateKey;
    @Value("${imagekit.endpoint}")
    private String imageKitEndpoint;
    private String apiEmailAddress;
    private String apiPassword;
    private String tlsInfo;


    public io.imagekit.sdk.config.Configuration createImageKitConnectionConfig(){
        return new io.imagekit.sdk.config.Configuration(
                imageKitPublicKey,
                imageKitPrivateKey,
                imageKitEndpoint
        );
    }

    @Bean
    public ImageKit createImageKit(){
        io.imagekit.sdk.config.Configuration config = new io.imagekit.sdk.config.Configuration(
                imageKitPublicKey,
                imageKitPrivateKey,
                imageKitEndpoint
        );
        ImageKit imageKit = ImageKit.getInstance();
        imageKit.setConfig(config);
        return imageKit;
    }

    @Bean
    public JavaMailSender createJavaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername(apiEmailAddress);
        javaMailSender
        return new JavaMailSenderImpl();
    }

    @Bean
    public TemplateEngine createTemplateEngine(){
        return new TemplateEngine();
    }

}
