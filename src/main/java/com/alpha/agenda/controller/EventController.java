package com.alpha.agenda.controller;

import com.alpha.agenda.dto.CreateEventRequest;
import com.alpha.agenda.entity.Event;
import com.alpha.agenda.entity.User;
import com.alpha.agenda.repository.EventRepository;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventRepository eventRepository;

    @PostMapping
    public Event createEvent(
            @Valid @RequestBody CreateEventRequest request,
            Authentication authentication
    ) {

        User user = (User) authentication.getPrincipal();

        Event event = new Event();

        event.setTitle(
                request.getTitle()
        );

        event.setDescription(
                request.getDescription()
        );

        event.setStartDate(
                LocalDateTime.parse(
                        request.getStartDate()
                )
        );

        event.setEndDate(
                LocalDateTime.parse(
                        request.getEndDate()
                )
        );

        event.setCategory(
                request.getCategory()
        );

        event.setReminderMinutes(
                request.getReminderMinutes()
        );

        event.setMemory(
                request.getMemory() != null
                        ? request.getMemory()
                        : false
        );

        event.setUser(user);

        return eventRepository.save(event);
    }

    @GetMapping
    public List<Event> getEvents(
            Authentication authentication
    ) {

        User user = (User) authentication.getPrincipal();

        return eventRepository.findByUser(user);
    }

    @DeleteMapping("/{id}")
    public String deleteEvent(
            @PathVariable Long id,
            Authentication authentication
    ) {

        User user = (User) authentication.getPrincipal();

        Event event = eventRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Event not found")
                );

        if (!event.getUser().getId().equals(user.getId())) {

            throw new RuntimeException("Unauthorized");
        }

        eventRepository.delete(event);

        return "Event deleted";
    }

    @PutMapping("/{id}")
    public Event updateEvent(
            @PathVariable Long id,
            @RequestBody CreateEventRequest request,
            Authentication authentication
    ) {

        User user = (User) authentication.getPrincipal();

        Event event = eventRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Event not found")
                );

        if (!event.getUser().getId().equals(user.getId())) {

            throw new RuntimeException("Forbidden");
        }

        event.setTitle(
                request.getTitle()
        );

        event.setDescription(
                request.getDescription()
        );

        event.setCategory(
                request.getCategory()
        );

        event.setReminderMinutes(
                request.getReminderMinutes()
        );

        event.setMemory(
                request.getMemory() != null
                        ? request.getMemory()
                        : false
        );

        event.setStartDate(
                LocalDateTime.parse(
                        request.getStartDate()
                )
        );

        event.setEndDate(
                LocalDateTime.parse(
                        request.getEndDate()
                )
        );

        return eventRepository.save(event);
    }
}