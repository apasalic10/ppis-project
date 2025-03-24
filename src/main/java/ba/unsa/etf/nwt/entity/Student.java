package ba.unsa.etf.nwt.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="students")
@PrimaryKeyJoinColumn(name = "user_id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Student extends User {
    private String experience;
}
