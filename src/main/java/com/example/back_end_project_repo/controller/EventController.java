package com.example.back_end_project_repo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.back_end_project_repo.models.Event;
import com.example.back_end_project_repo.services.EventServices;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventServices eventServices;

    // GET all events
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventServices.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    // GET event by ID
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Optional<Event> event = eventServices.getEventById(id);
        if (event.isPresent()) {
            return new ResponseEntity<>(event.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // POST add a new event
    @PostMapping
    public ResponseEntity<Event> addEvent(@RequestBody Event event) {
        Event createdEvent = eventServices.addEvent(event);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    // PUT update an event
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event eventDetails) {
        Optional<Event> existingEvent = eventServices.getEventById(id);
        if (existingEvent.isPresent()) {
            Event event = existingEvent.get();
            event.setTitle(eventDetails.getTitle());
            event.setDescription(eventDetails.getDescription());
            event.setDate(eventDetails.getDate());
            event.setTime(eventDetails.getTime());
            event.setAllDay(eventDetails.getAllDay());
            Event updatedEvent = eventServices.updateEvent(event);
            return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE an event
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        Optional<Event> event = eventServices.getEventById(id);
        if (event.isPresent()) {
            eventServices.deleteEvent(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
