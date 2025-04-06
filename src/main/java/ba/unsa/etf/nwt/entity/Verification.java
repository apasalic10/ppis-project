package ba.unsa.etf.nwt.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Document type is required")
    @Size(max = 100, message = "Document type must not exceed 100 characters")
    private String documentType;

    @NotBlank(message = "Document URL is required")
    @Pattern(
            regexp = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$",
            message = "Invalid URL format"
    )
    private String documentUrl;

    @NotBlank(message = "Status is required")
    @Pattern(
            regexp = "^(PENDING|APPROVED|REJECTED)$",
            message = "Status must be either PENDING, APPROVED, or REJECTED"
    )
    private String status;

    @PastOrPresent(message = "Submission date cannot be in the future") //probably is going to be added automatically with Date.now()
    private Date submissionDate;

    @PastOrPresent(message = "Review date cannot be in the future")
    private Date reviewDate;

    @Size(max = 500, message = "Reviewer notes must not exceed 500 characters")
    private String reviewerNotes;

    private Long reviewerId;

    @OneToOne
    @JoinColumn(name = "teacher_id")
    @NotNull(message = "Teacher is required")
    private Teacher teacher;

}
