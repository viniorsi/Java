package com.viniorsi.TravelEase.Service.Email;

import com.viniorsi.TravelEase.Domain.Email.EmailMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Collections;

import static org.mockito.Mockito.verify;

class EmailPublisherTest {

    @Mock
    private RabbitTemplate rabbitTemplateMock;

    @InjectMocks
    private EmailPublisher emailPublisher;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPublishEmail() {

        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setTo("recipient@example.com");
        emailMessage.setSubject("Test Subject");
        emailMessage.setBody("Test Body");
        emailMessage.setAttachments(Collections.emptyList());


        emailPublisher.publishEmail(emailMessage);


        verify(rabbitTemplateMock).convertAndSend(
                "emailExchange",
                "email.send",
                emailMessage
        );
    }
}