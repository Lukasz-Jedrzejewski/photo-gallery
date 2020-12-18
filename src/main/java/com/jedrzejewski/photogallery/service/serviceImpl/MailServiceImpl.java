package com.jedrzejewski.photogallery.service.serviceImpl;

import com.jedrzejewski.photogallery.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendGeneratedPassword(String recipient, String topic, String content) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(recipient);
        helper.setSubject(topic);
        helper.setText(content, true);
        javaMailSender.send(msg);
    }
}
