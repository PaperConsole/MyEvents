package com.MyEvents.model;

import com.MyEvents.dto.EventDto;
import jakarta.annotation.Generated;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String name;

   private String description;

   private int capacity;

   private LocalDate date;

   //TODO - set optional to false when LocationService implemented
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "location_id", nullable = true)
   private Location location;

   @OneToMany(mappedBy = "event")
   private Set<Registration> registrations = new HashSet<>();

    public Event() {
    }

    public Event(Long id, String name, String description, Location location, int capacity, LocalDate date,  Set<Registration> registrations) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.capacity = capacity;
        this.date = date;
        this.registrations = registrations;
    }
/*
    public EventDto toEventDto() {
        return new EventDto(this.getName(), this.getDescription(), this.getCapacity(), this.getLocation().getName(), this.getRegistrations().stream().map(Registration::getParticipant).toList().stream().map(Participant::getEmail).toList());
    }
*/
public EventDto toEventDto() {
    String locName = (this.getLocation() != null) ? this.getLocation().getName() : null;

    List<String> emails = (this.getRegistrations() == null)
            ? List.of()
            : this.getRegistrations().stream()
            .map(Registration::getParticipant)
            .filter(Objects::nonNull)
            .map(Participant::getEmail)
            .filter(Objects::nonNull)
            .toList();

    return new EventDto(this.getName(), this.getDescription(), this.getCapacity(), locName, this.getDate(), emails);
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
