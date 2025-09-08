package com.MyEvents.model;

import com.MyEvents.dto.ParticipantDto;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @OneToMany(mappedBy = "participant")
    private Set<Registration> registrations = new HashSet<>();

    public Participant() {

    }

    public Participant(Long id, String name, String email, Set<Registration> registrations) {
        this.id = id;
        this.name = name;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(Set<Registration> registrations) {
        this.registrations = registrations;
    }
}
