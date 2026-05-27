package com.alpha.agenda.repository;

import com.alpha.agenda.entity.Event;
import com.alpha.agenda.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByUser(User user);
}