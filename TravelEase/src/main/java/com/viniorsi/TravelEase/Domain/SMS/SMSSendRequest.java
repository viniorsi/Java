package com.viniorsi.TravelEase.Domain.SMS;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SMSSendRequest {

    private String destinationSMSNumber;
    private String message;

}
