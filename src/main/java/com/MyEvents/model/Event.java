package com.MyEvents.model;

import jakarta.annotation.Generated;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String name;

   private String description;

   @ManyToOne
   @JoinColumn(name = "location_id")
   private Location location;

   @OneToMany(mappedBy = "event")
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
