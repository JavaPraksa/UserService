package levi9.UserService.dto;

import levi9.UserService.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoggedUserDto {
    private String token;
    private UserRole role;
    private String username;
}
