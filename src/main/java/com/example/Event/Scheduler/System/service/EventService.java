package com.example.eventscheduler.service;

import com.example.eventscheduler.model.Event;
import com.example.eventscheduler.persistence.EventStorage;

import java.util.*;
import java.util.stream.Collectors;

public class EventService {
    private List<Event> events;

    public EventService() {
        this.events = EventStorage.loadEvents();
    }

    public List<Event> getAllEvents() {
        return events.stream()
                     .sorted(Comparator.comparing(Event::getStartTime))
                     .collect(Collectors.toList());
    }

    public Event addEvent(Event event) {
        event.setId(UUID.randomUUID().toString());
        events.add(event);
        EventStorage.saveEvents(events);
        return event;
    }

    public Event updateEvent(String id, Event updatedEvent) {
        for (Event e : events) {
            if (e.getId().equals(id)) {
                e.setTitle(updatedEvent.getTitle());
                e.setDescription(updatedEvent.getDescription());
                e.setStartTime(updatedEvent.getStartTime());
                e.setEndTime(updatedEvent.getEndTime());
                EventStorage.saveEvents(events);
                return e;
            }
        }
        return null;
    }

    public boolean deleteEvent(String id) {
        boolean removed = events.removeIf(e -> e.getId().equals(id));
        if (removed) EventStorage.saveEvents(events);
        return removed;
    }
}
