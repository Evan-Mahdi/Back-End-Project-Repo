package com.example.back_end_project_repo.services;

import com.example.back_end_project_repo.models.Event;
import com.example.back_end_project_repo.repositories.EventRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public class EventServices {

    @Autowired
    EventRepository eventRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //find all events
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    //find event by id
    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    //add event
    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    //update event
    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }

    //delete event
    public void deleteEvent(Long id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            eventRepository.deleteById(id);
        }
    }

    public void cleanUpEvebtTable() {
        jdbcTemplate.execute("SET SQL_SAFE_UPDATES = 0;");
        jdbcTemplate.execute("DELETE FROM events");
        jdbcTemplate.execute("ALTER TABLE events AUTO_INCREMENT = 1;");
        jdbcTemplate.execute("SET SQL_SAFE_UPDATES = 1;");
    }

    public void importJsonData(InputStream inputStream) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Event> events = objectMapper.readValue(inputStream, new TypeReference<List<Event>>() {
        });
        for (Event event : events) {
            eventRepository.save(event);
        }
    }
}