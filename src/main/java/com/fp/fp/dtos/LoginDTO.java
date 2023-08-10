package com.fp.fp.dtos;

import com.fp.fp.models.Roles;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDTO {
    public String username;
    public String password;
    public List<Roles> userRoles;
}
