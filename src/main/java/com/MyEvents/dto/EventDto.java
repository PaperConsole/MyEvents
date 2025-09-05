package com.MyEvents.dto;
import java.util.ArrayList;
import java.util.List;


public class EventDto {

        private String name;

        private String description;

        private int capacity;

        private String locationName;

        private List<String> registrationEmail = new ArrayList<>();

    public EventDto(String name, String description, int capacity, String locationName, List<String> registrationEmail) {
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.locationName = locationName;
        this.registrationEmail = registrationEmail;
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

    public List<String> getRegistrationEmail() {
        return registrationEmail;
    }

    public void setRegistrationEmail(List<String> registrationEmail) {
        this.registrationEmail = registrationEmail;
    }
}
