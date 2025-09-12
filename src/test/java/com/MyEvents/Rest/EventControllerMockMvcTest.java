package com.MyEvents.Rest;

import com.MyEvents.controller.EventController;
import com.MyEvents.dto.EventDto;
import com.MyEvents.mapper.EventMapper;
import com.MyEvents.model.Event;
import com.MyEvents.model.Location;
import com.MyEvents.service.EventService;
import com.MyEvents.service.LocationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.HashSet;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventController.class)
public class EventControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private EventService eventService;

    @MockitoBean
    private EventMapper eventMapper;

    @MockitoBean
    private LocationService locationService;


//get
    @Test
    void testFindEventById_Ok() throws Exception {
        Event event = new Event();
        event.setName("Andrzejki");

        EventDto dto = new EventDto();
        dto.setName("Andrzejki");

        when(eventService.findById(9L)).thenReturn(event);
        when(eventMapper.toEventDto(event)).thenReturn(dto);

        mockMvc.perform(get("/api/events/9"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Andrzejki"));
    }
    
//save
    @Test
    void createEvent_created_withLocationHeader_andBody() throws Exception {

        Location location = new Location();
        EventDto eventDto = new EventDto();
        eventDto.setName("Konferencja Java");
        eventDto.setCapacity(150);
        eventDto.setLocationId(42L);
        eventDto.setDate(LocalDate.of(2025, 11, 15));

        when(locationService.findLocationById(42L)).thenReturn(location);

        Event event = new Event();
        event.setName("Konferencja Java");
        event.setCapacity(150);
        event.setLocation(location);
        event.setDate(LocalDate.of(2025, 11, 15));
        ReflectionTestUtils.setField(event, "id", 5L);

        when(locationService.findLocationById(42L)).thenReturn(location);
        when(eventMapper.toEvent(any(EventDto.class))).thenReturn(event);
        when(eventService.save(any(Event.class))).thenReturn(5L);      // <— kluczowe
        when(eventService.findById(5L)).thenReturn(event);
        when(eventMapper.toEventDto(event)).thenReturn(eventDto);

        mockMvc.perform(post("/api/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(eventDto)))
                .andExpect(status().isCreated())
                .andExpect(header().string(HttpHeaders.LOCATION, "/api/events/5"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.capacity").value(150));
    }

    @Test
    void createEvent_MethodArgumentNotValid() throws Exception {

        EventDto eventDto = new EventDto();
        mockMvc.perform(post("/api/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(eventDto)))
                .andExpect(status().isBadRequest());

    }

    @Test
    void createEvent_MethodArgumentNotValid_CapacityLessThan1() throws Exception {

        EventDto eventDto = new EventDto();
        eventDto.setName("Konferencja");
        eventDto.setCapacity(0);
        eventDto.setLocationId(42L);
        eventDto.setDate(LocalDate.of(2025, 11, 15));
        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventDto)))
                .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.capacity").value("Capacity must be at least 1"))
                .andExpect(jsonPath("$.*").value(org.hamcrest.Matchers.hasSize(1)));

    }

    @Test
    void createEvent_MethodArgumentNotValid_DescritpionBlank() throws Exception {

        EventDto eventDto = new EventDto();
        eventDto.setName("");
        eventDto.setCapacity(150);
        eventDto.setLocationId(42L);
        eventDto.setDate(LocalDate.of(2025, 11, 15));
        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Name can not be empty"))
                .andExpect(jsonPath("$.*").value(org.hamcrest.Matchers.hasSize(1)));

    }

    @Test
    void deleteEvent_ok() throws Exception {
mockMvc.perform(delete("/api/events/120"))
        .andExpect(status().isNoContent());
verify(eventService).deleteById(120L);



    }



}
