package org.example._14_sieb_sokkeang_pp_web_homework003.service;

import org.example._14_sieb_sokkeang_pp_web_homework003.model.entity.Venue;
import org.example._14_sieb_sokkeang_pp_web_homework003.model.request.VenueRequest;

import java.util.List;

public interface VenueService {
     List<Venue> getAllVenues(Integer page, Integer size);
     Venue getVenueById(Integer id);
     Venue createVenue(VenueRequest venueRequest);
     Venue updateVenue(Integer id, VenueRequest venueRequest);
     void deleteVenue(Integer id);
}