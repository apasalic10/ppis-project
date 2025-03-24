package ba.unsa.etf.nwt.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="teachers")
@PrimaryKeyJoinColumn(name = "user_id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Teacher extends User {

    //reference on user table not needed because Inheritance annotation in User class specifies that
    private String specializations;
    private String credentials;
    private boolean verified;
    private Date verificationDate;

}
