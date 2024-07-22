package com.viniorsi.TravelEase.Service.Email;

import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EmailServiceTest {

    @Mock
    private JavaMailSender javaMailSenderMock;

    @InjectMocks
    private EmailService emailService;

    @Value("${spring.mail.username}")
    private String remetente;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(emailService, "remetente", "teste@teste.com");
    }

    @Test
    public void testEnviarEmailTexto() throws Exception {
        String destinatario = "recipient@example.com";
        String assunto = "Test Subject";
        String mensagemHtml = "<html><body><h1>Test Email</h1><p>This is a test email.</p></body></html>";
        String caminhoImagem = "src/Assets/email/ImagemTravelEaseEmail.jpg";

        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(javaMailSenderMock.createMimeMessage()).thenReturn(mimeMessage);

        String result = emailService.enviarEmailTexto(destinatario, assunto, mensagemHtml, Collections.singletonList(caminhoImagem));



        assertEquals("Email enviado", result);
    }

    @Test
    public void testErroEnviarEmailTextoSemImagem() throws Exception {
        String destinatario = "recipient@example.com";
        String assunto = "Test Subject";
        String mensagemHtml = "<html><body><h1>Test Email</h1><p>This is a test email.</p></body></html>";
        String caminhoImagem = "imagem";

        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(javaMailSenderMock.createMimeMessage()).thenReturn(mimeMessage);

        String result = emailService.enviarEmailTexto(destinatario, assunto, mensagemHtml, Collections.singletonList(caminhoImagem));



        assertEquals("Erro ao tentar enviar email: Imagem não encontrada", result);
    }
    @Test
    public void testErroEnviarEmailTextoSemHtml() throws Exception {
        String destinatario = "recipient@example.com";
        String assunto = "Test Subject";
        String mensagemHtml = null;
        String caminhoImagem = "imagem";

        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(javaMailSenderMock.createMimeMessage()).thenReturn(mimeMessage);

        String result = emailService.enviarEmailTexto(destinatario, assunto, mensagemHtml, Collections.singletonList(caminhoImagem));



        assertEquals("Erro ao tentar enviar email: Text must not be null", result);
    }
}
