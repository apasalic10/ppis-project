package ba.unsa.etf.nwt.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="review")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "service_agreement_id", nullable = false, unique = true)
    private ServiceAgreement serviceAgreement;

    @OneToOne
    @JoinColumn(name = "session_id", nullable = false, unique = true)
    private Session session;

    private String reviewerId;
    private String revieweeId;
    private int rating;
    private String comment;
    private Date submissionDate;
    private boolean isPublic;
    private boolean isRecommended;
}
