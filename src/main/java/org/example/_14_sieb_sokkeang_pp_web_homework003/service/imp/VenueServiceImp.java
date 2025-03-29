package org.example._14_sieb_sokkeang_pp_web_homework003.service.imp;
import lombok.RequiredArgsConstructor;
import org.example._14_sieb_sokkeang_pp_web_homework003.exception.ResourceNotFoundException;
import org.example._14_sieb_sokkeang_pp_web_homework003.model.entity.Venue;
import org.example._14_sieb_sokkeang_pp_web_homework003.model.request.VenueRequest;
import org.example._14_sieb_sokkeang_pp_web_homework003.repository.VenueRepository;
import org.example._14_sieb_sokkeang_pp_web_homework003.service.VenueService;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
public class VenueServiceImp implements VenueService {

    private final VenueRepository venueRepository;

    @Override
    public List<Venue> getAllVenues(Integer page, Integer size) {
        if (page < 1 || size < 1) {
            throw new IllegalArgumentException("Page and size must be greater than 0");
        }
        return venueRepository.getAllVenues(size, page);
    }

    @Override
    public Venue getVenueById(Integer venueId) {
        Venue venue = venueRepository.getVenueById(venueId);
        if (venue == null) {
            throw new ResourceNotFoundException("Venue with ID " + venueId + " not found");
        }
        return venue;
    }

    @Override
    public Venue createVenue(VenueRequest venueRequest) {
        if (venueRequest.getVenueName() == null || venueRequest.getLocation() == null) {
            throw new IllegalArgumentException("Venue name and location cannot be null");
        }
        return venueRepository.createVenue(venueRequest);
    }

    @Override
    public Venue updateVenue(Integer venueId, VenueRequest venueRequest) {
        // Check if venue exists
        Venue existingVenue = venueRepository.getVenueById(venueId);
        if (existingVenue == null) {
            throw new ResourceNotFoundException("Venue with ID " + venueId + " not found");
        }
        if (venueRequest.getVenueName() == null || venueRequest.getLocation() == null) {
            throw new IllegalArgumentException("Venue name and location cannot be null");
        }
        return venueRepository.updateVenue(venueId, venueRequest);
    }

    @Override
    public void deleteVenue(Integer venueId) {
        // Check if venue exists
        Venue existingVenue = venueRepository.getVenueById(venueId);
        if (existingVenue == null) {
            throw new ResourceNotFoundException("Venue with ID " + venueId + " not found");
        }
        venueRepository.deleteVenue(venueId);
    }
}