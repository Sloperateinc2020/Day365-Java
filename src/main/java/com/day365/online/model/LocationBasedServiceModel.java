package com.day365.online.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationBasedServiceModel {
    private String service;
    private List<String> additionalServices;
    private String icon;
    private String details;
    private String state;
    private String city;
    private String pincode;
    private String availableServices;
    private String imageUrl;
    private String color;
}
