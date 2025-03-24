package ba.unsa.etf.nwt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="verifications")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Verification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String documentType;
    private String documentUrl;
    private String status;
    private Date submissionDate;
    private Date reviewDate;
    private String reviewerNotes;
    private Long reviewerId;

    @OneToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
}
