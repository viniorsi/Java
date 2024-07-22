package com.viniorsi.TravelEase.Service.Email;

import com.viniorsi.TravelEase.Domain.Email.EmailMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)

class EmailConsumerTest {

    @InjectMocks
    EmailConsumer emailConsumer;

    @Mock
    private EmailService emailService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testReceiveEmailMessage() {

        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setTo("recipient@example.com");
        emailMessage.setSubject("Test Subject");
        emailMessage.setBody("Test Body");
        emailMessage.setAttachments(Collections.emptyList());


        emailConsumer.receiveEmailMessage(emailMessage);


        verify(emailService).enviarEmailTexto(
                emailMessage.getTo(),
                emailMessage.getSubject(),
                emailMessage.getBody(),
                emailMessage.getAttachments()
        );
    }



}