package org.example._14_sieb_sokkeang_pp_web_homework003.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class VenueRequest {
    @NotNull
    @NotBlank
    @Size(min = 1, max = 50, message ="Venue name must be between 1 and 50 characters")
    private String venueName;
    private String location;

}
