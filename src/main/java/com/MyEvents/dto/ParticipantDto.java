package com.MyEvents.dto;
import com.MyEvents.model.Registration;

import java.util.HashSet;
import java.util.Set;

public class ParticipantDto {
    private String name;
    private String email;
    private Set<Registration> registrations = new HashSet<>();
}
