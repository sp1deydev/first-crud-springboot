package com.test_springboot.test_springboot.dto.request;

import com.test_springboot.test_springboot.exception.ErrorCode;
import com.test_springboot.test_springboot.validator.DobConstraint;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 3, message = "INVALID_USERNAME")
    String username;
    @Size(min = 8, message = "INVALID_PASSWORD")
    String password;
    String firstName;
    String lastName;

    @DobConstraint(min = 19, message = "INVALID_DOB")
    LocalDate dob;
//    Set<Role> roles;
}
