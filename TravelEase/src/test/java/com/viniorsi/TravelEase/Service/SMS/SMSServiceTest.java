package com.viniorsi.TravelEase.Service.SMS;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SMSServiceTest {

    @InjectMocks
    private SMSService smsService;

    @Value("${TWILIO_ACCOUNT_SID}")
    private String ACCOUNT_SID = "testAccountSid";

    @Value("${TWILIO_AUTH_TOKEN}")
    private String AUTH_TOKEN = "testAuthToken";

    @Value("${TWILIO_OUTGOING_NUMBER}")
    private String OUTGOING_NUMBER = "testOutgoingNumber";

    @Mock
    private Twilio twilioMock;

    @BeforeEach
    void setUp() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    }

    @Test
    void whenMessageIsSent_thenStatusIsSent() {
        String destinationSMSNumber = "+1234567890";
        String smsMessage = "Test Message";
        try (MockedStatic<Message> mockedMessage = mockStatic(Message.class)) {
            MessageCreator mockMessageCreator = mock(MessageCreator.class);
            Message mockMessageInstance = mock(Message.class);
            when(mockMessageInstance.getStatus()).thenReturn(Message.Status.SENT);
            when(Message.creator(any(PhoneNumber.class), any(PhoneNumber.class), eq(smsMessage))).thenReturn(mockMessageCreator);
            when(mockMessageCreator.create()).thenReturn(mockMessageInstance);

            String status = smsService.sendSMS(destinationSMSNumber, smsMessage);

            assertEquals("sent", status);
        }
    }
    @Test
    public void testSetup() {
        MockitoAnnotations.openMocks(this);
        smsService = new SMSService();
        ReflectionTestUtils.setField(smsService, "ACCOUNT_SID", "your_account_sid");
        ReflectionTestUtils.setField(smsService, "AUTH_TOKEN", "your_auth_token");
        smsService.setup();
        Twilio.init("your_account_sid", "your_auth_token");
    }




}

