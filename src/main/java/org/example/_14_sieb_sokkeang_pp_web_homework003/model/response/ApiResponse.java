package org.example._14_sieb_sokkeang_pp_web_homework003.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ApiResponse<T> {
    private String message;
    private T payload;
    private HttpStatus status;
    private LocalDateTime timestamp;
}