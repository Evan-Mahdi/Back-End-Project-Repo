package com.example.back_end_project_repo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.back_end_project_repo.models.Event;
import com.example.back_end_project_repo.repositories.EventRepository;

@Service
public class EventServices {

    @Autowired
    EventRepository eventRepository;

    //find all events
    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    //find event by id
    public Optional<Event> getEventById(Long id){
        return eventRepository.findById(id);
    }

    //add event
    public Event addEvent(Event event){
        return eventRepository.save(event);
    }

    //update event
    public Event updateEvent(Event event){
        return eventRepository.save(event);
    }

    //delete event
	public void deleteEvent(Long id) {
		Optional<Event> optionalEvent = eventRepository.findById(id);
		if(optionalEvent.isPresent()) {
			eventRepository.deleteById(id);
		}
	}
}