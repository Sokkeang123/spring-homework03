package org.example._14_sieb_sokkeang_pp_web_homework003.controller;

import lombok.RequiredArgsConstructor;
import org.example._14_sieb_sokkeang_pp_web_homework003.model.entity.Event;
import org.example._14_sieb_sokkeang_pp_web_homework003.model.request.EventRequest;
import org.example._14_sieb_sokkeang_pp_web_homework003.model.request.EventUpdateRequest;
import org.example._14_sieb_sokkeang_pp_web_homework003.model.response.ApiResponse;
import org.example._14_sieb_sokkeang_pp_web_homework003.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/event-controller")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    // Get all events with pagination
    @GetMapping
    public ResponseEntity<ApiResponse<List<Event>>> getAllEvents(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        ApiResponse<List<Event>> response = ApiResponse.<List<Event>>builder()
                .message("Get All Events Successfully")
                .payload(eventService.getAllEvents(page, size))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    // Get event by ID
    @GetMapping("/{event-id}")
    public ResponseEntity<ApiResponse<Event>> getEventById(@PathVariable("event-id") Integer eventId) {
        Event event = eventService.getEventById(eventId);
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .message("Get Event by ID Successfully")
                .payload(event)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    // Create a new event
    @PostMapping
    public ResponseEntity<ApiResponse<Event>> createEvent(@RequestBody EventRequest eventRequest) {
        Event createdEvent = eventService.createEvent(eventRequest);

        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .message("Create Event Successfully")
                .payload(createdEvent)
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    // Update an event
    @PutMapping("/{event-id}")
    public ResponseEntity<ApiResponse<Event>> updateEvent(
            @PathVariable("event-id") Integer eventId,
            @RequestBody EventUpdateRequest eventUpdateRequest) {

        Event updatedEvent = eventService.updateEvent(eventId, eventUpdateRequest);

        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .message("Update Event Successfully")
                .payload(updatedEvent)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    // Delete an event
    @DeleteMapping("/{event-id}")
    public ResponseEntity<ApiResponse<String>> deleteEvent(@PathVariable("event-id") Integer eventId) {
        eventService.deleteEventById(eventId);
        ApiResponse<String> response = ApiResponse.<String>builder()
                .message("Delete Event Successfully")
                .payload("Event with ID " + eventId + " has been deleted")
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }
}