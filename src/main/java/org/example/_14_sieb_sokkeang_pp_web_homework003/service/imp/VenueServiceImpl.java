package org.example._14_sieb_sokkeang_pp_web_homework003.service.imp;

import org.example._14_sieb_sokkeang_pp_web_homework003.model.entity.Venue;
import org.example._14_sieb_sokkeang_pp_web_homework003.model.request.VenueRequest;
import org.example._14_sieb_sokkeang_pp_web_homework003.repository.VenueRepository;
import org.example._14_sieb_sokkeang_pp_web_homework003.service.VenueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueServiceImpl implements VenueService {
    private final VenueRepository venueRepository;

    public VenueServiceImpl(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public List<Venue> getAllVenues(Integer page, Integer size) {
        return venueRepository.getAllVenues(size, page);
    }

    @Override
    public Venue getVenueById(Integer id) {
        return venueRepository.getVenueById(id);
    }

    @Override
    public Venue createVenue(VenueRequest venueRequest) {
        return venueRepository.createVenue(venueRequest);
    }

    @Override
    public Venue updateVenue(Integer id, VenueRequest venueRequest) {
        Venue existingVenue = venueRepository.getVenueById(id);
        if (existingVenue == null) {
            return null;
        }
        return venueRepository.updateVenue(id, venueRequest);
    }

    @Override
    public void deleteVenue(Integer id) {
        venueRepository.deleteVenue(id);
    }
}