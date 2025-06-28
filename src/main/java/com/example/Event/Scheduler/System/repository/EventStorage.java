package com.example.eventscheduler.persistence;

import com.example.eventscheduler.model.Event;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EventStorage {
    private static final String FILE_NAME = "events.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    public static List<Event> loadEvents() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) return new ArrayList<>();
            return mapper.readValue(file, new TypeReference<List<Event>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void saveEvents(List<Event> events) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_NAME), events);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
