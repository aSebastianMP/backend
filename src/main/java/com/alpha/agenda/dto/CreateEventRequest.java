package com.alpha.agenda.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEventRequest {

    @NotBlank
    private String title;

    private String description;

    private String startDate;

    private String endDate;

    private String category;

    private Integer reminderMinutes;

    private Boolean memory;
}