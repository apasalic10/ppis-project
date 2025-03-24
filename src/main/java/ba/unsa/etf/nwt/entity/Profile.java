package ba.unsa.etf.nwt.entity;

import jakarta.persistence.*;

@Entity
@Table(name="profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String location;
    private String education;
    private int yearsOfExperience;
    private String preferences;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
