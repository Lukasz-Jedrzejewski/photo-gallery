package com.jedrzejewski.photogallery.service;

import javax.mail.MessagingException;

public interface MailService {

    void sendGeneratedPassword(String recipient, String topic, String content) throws MessagingException;
}
