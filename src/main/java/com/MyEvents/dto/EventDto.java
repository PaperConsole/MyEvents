package com.MyEvents.dto;
import com.MyEvents.model.Event;
import com.MyEvents.model.Location;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class EventDto {

        private String name;

        private String description;

        private int capacity;

        private String locationName;

        private LocalDate date;

        private List<String> registrationEmail = new ArrayList<>();

    public EventDto(String name, String description, int capacity, String locationName, LocalDate date, List<String> registrationEmail) {
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.locationName = locationName;
        this.date = date;
        this.registrationEmail = registrationEmail;
    }

    public Event toEvent() {
        Event event = new Event();
        event.setName(this.getName());
        event.setCapacity(this.getCapacity());
        event.setDescription(this.getDescription());
        event.setDate(this.getDate());

       // event.setLocation(new Location()); //TODO implement LocationService class and findByName method
       // event.setRegistrations(new HashSet<>()); //TODO implement RegistrationsService class to match emails-participants-registrations
        return event;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<String> getRegistrationEmail() {
        return registrationEmail;
    }

    public void setRegistrationEmail(List<String> registrationEmail) {
        this.registrationEmail = registrationEmail;
    }
}
