package ba.unsa.etf.nwt.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="session")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "service_agreement_id", nullable = false)
    private ServiceAgreement serviceAgreement;

    private Date startTime;
    private Date endTime;
    private String status;
    private String location;
    private String meetingLink;
    private String sessionNotes;
    private String teacherPreparationNotes;
    private String studentPreparationNotes;
}
