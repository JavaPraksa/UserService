package levi9.UserService.model;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Data
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
}