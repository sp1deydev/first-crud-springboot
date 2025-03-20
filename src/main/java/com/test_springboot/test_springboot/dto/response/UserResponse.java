package com.test_springboot.test_springboot.dto.response;

import com.test_springboot.test_springboot.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse implements Serializable {
    String username;
    String firstName;
    String lastName;
    LocalDate dob;
//    Set<Role> roles;
}
