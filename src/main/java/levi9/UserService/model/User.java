package levi9.UserService.model;

import levi9.UserService.model.enums.UserRole;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "USERS")
public class User extends BaseEntity {

    private String firstName;

    private String lastName;

    @Column(nullable = false)
    private UserRole role;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String email;
}
