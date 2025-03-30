package org.example._14_sieb_sokkeang_pp_web_homework003.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendeeRequest {

    @NotBlank
    @NotNull
    @Size(min = 1, max = 50 , message = "Attendee Name must between 1 and 50 character")
    private String attendeeName;
    @Email
    @NotNull
    @NotBlank
    private String email;
}