package levi9.UserService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto extends UserDto{
    private String password;
    private String confirmPassword;

    public boolean passwordsNotEqual(){
        return !password.equals(confirmPassword);
    }
}
