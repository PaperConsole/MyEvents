package com.MyEvents.mapper;

import com.MyEvents.dto.EventDto;
import com.MyEvents.model.Event;
import com.MyEvents.model.Location;
import com.MyEvents.model.Participant;
import com.MyEvents.model.Registration;
import com.MyEvents.service.LocationService;
import com.MyEvents.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EventMapper {

    @Autowired
    LocationService locationService;

    @Autowired
    RegistrationService registrationService;

    public EventMapper(LocationService locationService, RegistrationService registrationService) {
        this.locationService = locationService;
        this.registrationService = registrationService;
    }

    public EventDto toEventDto(Event event) {

        return new EventDto(event.getName(), event.getDescription(), event.getCapacity(), event.getLocation().getId(), event.getDate(), event.getRegistrations().stream().map(Registration::getId).collect(Collectors.toSet()));
    }

    public Event toEvent(EventDto eventDto) {

        Location location = locationService.findLocationById(eventDto.getLocationId());
        Set<Registration> registrations = new HashSet<>();
        for(Long registrationId : eventDto.getRegistrationId()) {
            registrations.add(registrationService.findById(registrationId));
        }

        Event event = new Event();
        event.setName(eventDto.getName());
        event.setCapacity(eventDto.getCapacity());
        event.setDescription(eventDto.getDescription());
        event.setDate(eventDto.getDate());
        event.setLocation(location);
        event.setRegistrations(registrations);
        return event;
    }



}
