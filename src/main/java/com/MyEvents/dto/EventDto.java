package com.MyEvents.dto;
import com.MyEvents.model.Event;
import com.MyEvents.model.Location;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class EventDto {

    @NotBlank(message = "Name can not be empty")
        private String name;

        private String description;

    @Min(value = 1, message = "Capacity must be at least 1")
        private int capacity;

        private long locationId;

    @Future(message = "Date can not be of today and previous days")
        private LocalDate date;

        private Set<Long> registrationId = new HashSet<>();

    public EventDto(String name, String description, int capacity, long locationId, LocalDate date, Set<Long> registrationId) {
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.locationId = locationId;
        this.date = date;
        this.registrationId = registrationId;
    }

    public EventDto() {

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

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<Long> getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Set<Long> registrationId) {
        this.registrationId = registrationId;
    }
}
