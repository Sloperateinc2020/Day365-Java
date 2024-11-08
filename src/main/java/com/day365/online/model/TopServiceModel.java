package com.day365.online.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopServiceModel {
    private String title;
    private Long jobCount;
    private String location;
    private String iconUrl;
    private boolean isPopularService;
}
