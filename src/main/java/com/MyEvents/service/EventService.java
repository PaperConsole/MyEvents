package com.MyEvents.service;

import com.MyEvents.dto.EventDto;
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

    public Event create(Event newEvent) {
      Event event =  eventRepository.save(newEvent);
      return event;
    }

    public List<EventDto> findAll() {
        return eventRepository.findAll().stream().map(Event::toEventDto).toList();
    }

    public EventDto findById(Long id) {
        return eventRepository
                .findById(id)
                .map(Event::toEventDto)
                .orElseThrow(() -> new RuntimeException("Not found"));
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

    public long save(EventDto eventDto) {
        Event event = eventDto.toEvent();
        eventRepository.save(event);
        return event.getId();

    }

    public int checkAvailableSeats(Event event) {
     return event.getCapacity() - event.getRegistrations().size();
    }

}
