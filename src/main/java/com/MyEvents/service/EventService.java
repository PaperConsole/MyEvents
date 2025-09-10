package com.MyEvents.service;

import com.MyEvents.dto.EventDto;
import com.MyEvents.exception.EventNotFoundException;
import com.MyEvents.mapper.EventMapper;
import com.MyEvents.model.Event;
import com.MyEvents.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EventService {

    @Autowired
    private final EventRepository eventRepository;


    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;

    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Event findById(Long id) {
        return eventRepository
                .findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
    }

    public Event findByNameContainingIgnoreCase(String name) {
        return eventRepository.findByNameContainingIgnoreCase(name.trim()).stream().findFirst()
                .orElseThrow(() -> new EventNotFoundException(name));
    }

    /* TODO - fix update method
    public EventDto update(Event updatedEvent) {
        Event eventToUpdate = findById(updatedEvent.getId());
        eventToUpdate.setName(updatedEvent.getName());
        eventToUpdate.setCapacity(updatedEvent.getCapacity());
        eventToUpdate.setDescription(updatedEvent.getDescription());
        eventToUpdate.setLocation(updatedEvent.getLocation());
        eventToUpdate.setRegistrations(updatedEvent.getRegistrations());
        return eventRepository.save(eventToUpdate);
    }
*/
    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

    public long save(Event event) {
        return eventRepository.save(event).getId();

    }

    public int checkAvailableSeats(Event event) {
     return event.getCapacity() - event.getRegistrations().size();
    }

}
