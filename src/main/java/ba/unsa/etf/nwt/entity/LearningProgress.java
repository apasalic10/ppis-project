package ba.unsa.etf.nwt.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "learning_progress")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LearningProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
    private String uuid;

    @OneToOne
    @JoinColumn(name = "service_agreement_id", nullable = false, unique = true)
    private ServiceAgreement serviceAgreement;

    private String studentId;
    private String skillName;
    private int initialLevel;
    private int currentLevel;
    private String teacherFeedback;
    private String studentNotes;
    private Date assessmentDate;
}
