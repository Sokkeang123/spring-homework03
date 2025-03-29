package org.example._14_sieb_sokkeang_pp_web_homework003.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventAttendee {

    private Integer id;
    private Integer eventId;
    private Integer attendeeId;


}
