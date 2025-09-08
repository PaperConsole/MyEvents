package com.MyEvents.controller;

import com.MyEvents.dto.ParticipantDto;
import com.MyEvents.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipantDto> GetParticipantById(@PathVariable long id) {
        return ResponseEntity.ok(participantService.getParticipantById(id));
    }

    @PostMapping("")
    public ResponseEntity<ParticipantDto> addParticipant(@RequestBody ParticipantDto participantDto) throws URISyntaxException {
        Long uriId = participantService.save(participantDto);
        return ResponseEntity.created(new URI("/api/participants" + uriId)).body(participantService.getParticipantById(uriId));
    }


}
