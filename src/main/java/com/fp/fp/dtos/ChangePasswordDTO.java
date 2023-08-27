package com.fp.fp.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangePasswordDTO {
    private String password;
    private String newPassword;
    private String confirmNewPassword;
}
