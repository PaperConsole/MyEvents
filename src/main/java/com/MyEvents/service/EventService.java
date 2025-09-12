package com.MyEvents.service;

import com.MyEvents.dto.EventDto;
import com.MyEvents.exception.EventNotFoundException;
import com.MyEvents.exception.LocationNotFoundException;
import com.MyEvents.mapper.EventMapper;
import com.MyEvents.model.Event;
import com.MyEvents.model.Location;
import com.MyEvents.repository.EventRepository;
import com.MyEvents.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EventService {

    @Autowired
    private final EventRepository eventRepository;

    @Autowired
    private final LocationRepository locationRepository;


    public EventService(EventRepository eventRepository, LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;

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

    public Event updateEvent(Long id, EventDto dto) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));

        event.setName(dto.getName());
        event.setDescription(dto.getDescription());
        event.setCapacity(dto.getCapacity());
        event.setDate(dto.getDate());

        if (dto.getLocationId() > 0) {
            Location location = locationRepository.findById(dto.getLocationId())
                    .orElseThrow(() -> new LocationNotFoundException(dto.getLocationId()));
            event.setLocation(location);
        }

        return eventRepository.save(event);
    }

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
