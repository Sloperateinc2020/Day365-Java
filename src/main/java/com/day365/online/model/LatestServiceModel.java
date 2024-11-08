package com.day365.online.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LatestServiceModel {
    private String title;
    private Map<String, Integer> salaryRange;
    private Map<String, String> location;
    private String workType;
    private String iconUrl;
    private String badge;
}
