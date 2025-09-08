package com.MyEvents.repository;

import com.MyEvents.model.Event;
import com.MyEvents.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    List<Participant> findByEmailContainingIgnoreCase(String fragment);
}
