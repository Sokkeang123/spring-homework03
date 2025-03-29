package org.example._14_sieb_sokkeang_pp_web_homework003.controller;
import lombok.RequiredArgsConstructor;
import org.example._14_sieb_sokkeang_pp_web_homework003.model.entity.Attendee;
import org.example._14_sieb_sokkeang_pp_web_homework003.model.request.AttendeeRequest;
import org.example._14_sieb_sokkeang_pp_web_homework003.model.response.ApiResponse;
import org.example._14_sieb_sokkeang_pp_web_homework003.service.AttendeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
@RestController
@RequestMapping("api/v1/attendees")
@RequiredArgsConstructor
public class AttendeeController {

    private final AttendeeService attendeeService;

    // Get all attendees with pagination
    @GetMapping
    public ResponseEntity<ApiResponse<List<Attendee>>> getAllAttendees(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        ApiResponse<List<Attendee>> response = ApiResponse.<List<Attendee>>builder()
                .message("Get All Attendees Successfully")
                .payload(attendeeService.getAllAttendees(page, size))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }
    // Get attendee by ID
    @GetMapping("/{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>> getAttendeeById(@PathVariable("attendee-id") Integer attendeeId) {
        Attendee attendee = attendeeService.getAttendeeById(attendeeId);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .message("Get Attendee by ID Successfully")
                .payload(attendee)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    // Create a new attendee
    @PostMapping
    public ResponseEntity<ApiResponse<Attendee>> createAttendee(@RequestBody AttendeeRequest attendeeRequest) {
        Attendee createdAttendee = attendeeService.createAttendee(attendeeRequest);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .message("Create Attendee Successfully")
                .payload(createdAttendee)
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Update attendee by ID
    @PutMapping("/{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>> updateAttendee(
            @PathVariable("attendee-id") Integer attendeeId,
            @RequestBody AttendeeRequest attendeeRequest
    ) {
        Attendee updatedAttendee = attendeeService.updateAttendee(attendeeId, attendeeRequest);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .message("Update Attendee Successfully")
                .payload(updatedAttendee)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    // Delete attendee by ID
    @DeleteMapping("/{attendee-id}")
    public ResponseEntity<ApiResponse<String>> deleteAttendee(@PathVariable("attendee-id") Integer attendeeId) {
        attendeeService.deleteAttendee(attendeeId);
        ApiResponse<String> response = ApiResponse.<String>builder()
                .message("Delete Attendee Successfully")
                .payload("Attendee with ID " + attendeeId + " has been deleted")
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }
}