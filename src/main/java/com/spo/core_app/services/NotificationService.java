package com.spo.core_app.services;

import com.spo.core_app.constants.EmailConstant;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@Slf4j
public class NotificationService {

    private TemplateEngine templateEngine;
    private JavaMailSender javaMailSender;

    @Autowired
    public NotificationService(TemplateEngine templateEngine,
                               JavaMailSender javaMailSender){
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
    }

    public void sendProcurementCompanyRegNotification(String toEmailAddress,
                                                      String userName,
                                                      String companyName,
                                                      String password){
        Context context = new Context();
        context.setVariable("maintUserName" , userName);
        context.setVariable("email", toEmailAddress);
        context.setVariable("temporaryPassword", password);
        String htmlEmail =  templateEngine.process(EmailConstant.PROCUREMENT_COMPANY_REG_EMAIL_TEMPLATE_NAME, context);
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setTo(toEmailAddress);
            mimeMessageHelper.setText(htmlEmail, true);
            mimeMessageHelper.setSubject(EmailConstant.PROCUREMENT_COMPANY_EMAIL_SUBJECT_LINE);
            javaMailSender.send(mimeMessage);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

}
