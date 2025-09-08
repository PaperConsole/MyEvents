package com.MyEvents.dto;
import com.MyEvents.model.Participant;
import com.MyEvents.model.Registration;
import com.MyEvents.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class ParticipantDto {
    private String name;
    private String email;
    private Set<Long> registrationId = new HashSet<>();

    public ParticipantDto(String name, String email, Set<Long> registrationId) {
        this.name = name;
        this.email = email;
        this.registrationId = registrationId;
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

    public Set<Long> getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Set<Long> registrationId) {
        this.registrationId = registrationId;
    }

}
