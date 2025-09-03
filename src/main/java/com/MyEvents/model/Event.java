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

   private int capacity;

   @ManyToOne
   @JoinColumn(name = "location_id")
   private Location location;

   @OneToMany(mappedBy = "event")
   private Set<Registration> registrations = new HashSet<>();

    public Event() {
    }

    public Event(Long id, String name, String description, Location location, int capacity, Set<Registration> registrations) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.capacity = capacity;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setRegistrations(Set<Registration> registrations) {
        this.registrations = registrations;


    }
}
