package com.MyEvents.dto;
import com.MyEvents.model.Participant;
import com.MyEvents.model.Registration;

import java.util.HashSet;
import java.util.Set;

public class ParticipantDto {
    private String name;
    private String email;
    private Set<String> registrationEventName = new HashSet<>();

    public ParticipantDto(String name, String email, Set<String> registrationEventName) {
        this.name = name;
        this.email = email;
        this.registrationEventName = registrationEventName;
    }

    public Participant toParticipant() {
        Participant participant = new Participant();
        participant.setEmail(email);
        participant.setName(name);

        // participant.setRegistrations(registrations); TODO implement later
        return participant;
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


}
