package com.spo.core_app.configurations;


import io.imagekit.sdk.ImageKit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;


import java.util.Properties;

@Configuration
public class SystemConfiguration {

    @Value("${imagekit.public.key}")
    private String imageKitPublicKey;
    @Value("${imagekit.private.key}")
    private String imageKitPrivateKey;
    @Value("${imagekit.endpoint}")
    private String imageKitEndpoint;
    @Value("${spring.mail.username}")
    private String apiEmailAddress;
    @Value("${spring.mail.password}")
    private String apiEmailPassword;


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
        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", true);
        mailProperties.put("mail.smtp.starttls.enable", true);
        javaMailSender.setJavaMailProperties(mailProperties);
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername(apiEmailAddress);
        javaMailSender.setPassword(apiEmailPassword);
        return javaMailSender;
    }

    @Bean
    public TemplateEngine createTemplateEngine(){
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/"); // Make sure this folder exists in resources
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCharacterEncoding("UTF-8");
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }

}
