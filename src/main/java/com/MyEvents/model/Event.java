package com.MyEvents.model;

import java.util.HashSet;
import java.util.Set;

public class Event {
   private Long id;
   private String name;
   private String description;
   private Location location;
   private Set<Registration> registrations = new HashSet<>();

    public Event(Long id, String name, String description, Location location, Set<Registration> registrations) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.registrations = registrations;
    }

    public Long getId() {
        return id;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(Set<Registration> registrations) {
        this.registrations = registrations;
    }
}
