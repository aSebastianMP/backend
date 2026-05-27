package com.alpha.agenda.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String category;

    private Integer reminderMinutes;

    private Boolean memory = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}