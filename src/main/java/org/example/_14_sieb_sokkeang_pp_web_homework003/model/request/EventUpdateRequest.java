package org.example._14_sieb_sokkeang_pp_web_homework003.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventUpdateRequest {
    private String eventName;
    private Date eventDate;
    private Integer venueId;  // Changed from Venue to Integer (just ID)
    private List<Integer> attendeeIds;

}
