package levi9.UserService.dto;

import levi9.UserService.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String token;
    private UserRole role;
    private String username;
    private String firstName;
    private String lastName;
    private String email;

}
