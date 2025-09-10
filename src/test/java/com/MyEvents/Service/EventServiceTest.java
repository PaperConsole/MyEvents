package com.MyEvents.Service;


import com.MyEvents.model.Event;
import com.MyEvents.repository.EventRepository;
import com.MyEvents.service.EventService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.util.ReflectionTestUtils;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    EventRepository eventRepository;

    @InjectMocks
    EventService eventService;

    @Test
    public void findAllEventsTest_ok() {
        Event event = new Event();
        ReflectionTestUtils.setField(event, "id", 5L);
        event.setName("Impreza rodzinna");

        Event event2 = new Event();
        ReflectionTestUtils.setField(event2, "id", 6L );
        event2.setName("Impreza firmowa");

        when(eventRepository.findAll()).thenReturn(List.of(event, event2));

            List<Event> events = eventService.findAll();
            assertEquals(2, events.size());
            assertEquals("Impreza rodzinna", events.get(0).getName());
            assertEquals("Impreza firmowa", events.get(1).getName());
    }

    @Test
    public void findEventByIdTest_ok() {
        Event event = new Event();
        event.setName("Impreza rodzinna");
        ReflectionTestUtils.setField(event, "id", 5L);

        when(eventRepository.findById(5L)).thenReturn(Optional.of(event));
        Event found = eventService.findById(5L);
        assertEquals("Impreza rodzinna", found.getName());
    }

    @Test
    public void deleteByIdTest_ok() {
        eventService.deleteById(5L);
        verify(eventRepository).deleteById(5L);

    }

    @Test
    public void saveEventTest_ok() {
        Event event = new Event();
        event.setName("Impreza rodzinna");

        when(eventRepository.save(any(Event.class))).thenAnswer(inv -> {
            Event e = inv.getArgument(0);
            ReflectionTestUtils.setField(e, "id", 5L);
            return e;
        });
        Long id = eventService.save(event);
        assertEquals(5L, id);
        verify(eventRepository).save(event); // lub save(any(Event.class))
    }
    }

