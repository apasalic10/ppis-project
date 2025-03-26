package ba.unsa.etf.nwt.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="service_agreement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ServiceAgreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
    private String uuid;

    private String teacherId;
    private String studentId;
    private String listingId;
    private String status;
    private Date creationDate;
    private Date startDate;
    private Date endDate;
    private String terms;
    private boolean autoRenew;

    @OneToOne(mappedBy = "serviceAgreement", cascade = CascadeType.ALL, orphanRemoval = true)
    private Invoice invoice;

    @OneToOne(mappedBy = "serviceAgreement", cascade = CascadeType.ALL, orphanRemoval = true)
    private LearningProgress learningProgress;

    @OneToOne(mappedBy = "serviceAgreement", cascade = CascadeType.ALL, orphanRemoval = true)
    private Payment payment;

    @OneToMany(mappedBy = "serviceAgreement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Session> sessions = new ArrayList<>();

    @OneToMany(mappedBy = "serviceAgreement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> review = new ArrayList<>();

}
