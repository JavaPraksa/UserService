package levi9.UserService.model;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Data
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity {
    @Id
    @SequenceGenerator(name = "seqGen", sequenceName = "seq1", initialValue = 4, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
    private Long id;
}