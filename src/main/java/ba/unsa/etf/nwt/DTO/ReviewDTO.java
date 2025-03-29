package ba.unsa.etf.nwt.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private Long id;
    private String reviewerId;
    private String revieweeId;
    private int rating;
    private String comment;
    private String serviceAgreementStatus;
    private String sessionStatus;
}
