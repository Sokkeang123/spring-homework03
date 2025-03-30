package org.example._14_sieb_sokkeang_pp_web_homework003.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.example._14_sieb_sokkeang_pp_web_homework003.model.entity.Venue;
import org.example._14_sieb_sokkeang_pp_web_homework003.model.request.VenueRequest;
import org.example._14_sieb_sokkeang_pp_web_homework003.model.response.ApiResponse;
import org.example._14_sieb_sokkeang_pp_web_homework003.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/venues")
@RequiredArgsConstructor
public class VenueController {

    private final VenueService venueService;

    // Get all venues with pagination
    @GetMapping
    public ResponseEntity<ApiResponse<List<Venue>>> getAllVenues(
            @RequestParam(defaultValue = "1") @Positive Integer page,
            @RequestParam(defaultValue = "10") @Positive Integer size
    ) {
        ApiResponse<List<Venue>> response = ApiResponse.<List<Venue>>builder()
                .message("Get All Venues Successfully")
                .payload(venueService.getAllVenues(page, size))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    // Get venue by ID
    @GetMapping("/{venue-id}")
    public ResponseEntity<ApiResponse<Venue>> getVenueById(@PathVariable("venue-id") Integer venueId) {
        Venue venue = venueService.getVenueById(venueId);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .message("Get Venue by ID Successfully")
                .payload(venue)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();


        return ResponseEntity.ok(response);
    }

    // Create a new venue
    @PostMapping
    public ResponseEntity<ApiResponse<Venue>> createVenue(@RequestBody @Valid VenueRequest venueRequest) {
        Venue createdVenue = venueService.createVenue(venueRequest);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .message("Create Venue Successfully")
                .payload(createdVenue)
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();

        return  ResponseEntity.ok(response);
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Update venue by ID
    @PutMapping("/{venue-id}")
    public ResponseEntity<ApiResponse<Venue>> updateVenue(
            @PathVariable("venue-id") Integer venueId,
            @RequestBody VenueRequest venueRequest
    ) {
        Venue updatedVenue = venueService.updateVenue(venueId, venueRequest);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .message("Update Venue Successfully")
                .payload(updatedVenue)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    // Delete venue by ID
    @DeleteMapping("/{venue-id}")
    public ResponseEntity<ApiResponse<String>> deleteVenue(@PathVariable("venue-id") Integer venueId) {
        venueService.deleteVenue(venueId);
        ApiResponse<String> response = ApiResponse.<String>builder()
                .message("Delete Venue Successfully")
                .payload("Venue with ID " + venueId + " has been deleted")
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }
}