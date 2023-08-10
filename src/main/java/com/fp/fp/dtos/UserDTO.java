package com.fp.fp.dtos;

import com.fp.fp.models.Roles;
import jakarta.persistence.Basic;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private String username;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private String email;
    private List<Roles> userRole;
}
