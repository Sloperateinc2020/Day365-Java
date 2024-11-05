package com.day365.online.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    private String bookingId;
    private String userId;
    private String providerId;
    private String name;
    private String mobile;
    private String email;
    private String amountPaid;
    private String status;
    private String address;
    private String date;
    private String serviceType;
}