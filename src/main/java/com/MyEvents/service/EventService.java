package com.MyEvents.service;

import com.MyEvents.model.Event;
import com.MyEvents.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event create(Event newEvent) {
      Event event =  eventRepository.save(newEvent);
      return event;
    }

    public Event findById(Long id) {
        return eventRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    public Event update(Event updatedEvent) {
        Event eventToUpdate = findById(updatedEvent.getId());
        eventToUpdate.setName(updatedEvent.getName());
        eventToUpdate.setCapacity(updatedEvent.getCapacity());
        eventToUpdate.setDescription(updatedEvent.getDescription());
        eventToUpdate.setLocation(updatedEvent.getLocation());
        eventToUpdate.setRegistrations(updatedEvent.getRegistrations());
        return eventRepository.save(eventToUpdate);
    }
}
