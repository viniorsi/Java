package com.viniorsi.TravelEase.Domain.User.Service;

import jakarta.validation.constraints.Email;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class VerificationService {

    private static Map<String, String> verificationCodes = new HashMap<>();
    static EmailService emailService = new EmailService();

    public static String generateAndSendVerificationCode(String email) {
        String verificationCode = String.format("%06d", (int)(Math.random() * 1000000));
        verificationCodes.put(email, verificationCode);
        emailService.enviarEmailTexto(email, "Welcome to TravelEase!", "Hello! This is your code to finish your registration: " + verificationCode);
        return verificationCode;
    }


    public static boolean verifyCode(String email, String code) {
        String storedCode = verificationCodes.get(email);
        return storedCode != null && storedCode.equals(code);
    }
}


