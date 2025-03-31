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
public class ServiceAgreementPostDTO {
    private String teacherId;
    private String studentId;
    private String listingId;
    private String status;
    private Date startDate;
    private Date endDate;
    private String terms;
    private boolean autoRenew;
}
