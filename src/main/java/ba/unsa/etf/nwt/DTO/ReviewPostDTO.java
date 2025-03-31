package ba.unsa.etf.nwt.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewPostDTO {
    private Long serviceAgreementId;
    private Long sessionId;
    private String reviewerId;
    private String revieweeId;
    private int rating;
    private String comment;
    private Date submissionDate;
    private boolean isPublic;
    private boolean isRecommended;
}
