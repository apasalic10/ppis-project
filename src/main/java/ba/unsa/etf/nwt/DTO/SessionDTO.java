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
public class SessionDTO {
    private Long id;
    private Date startTime;
    private Date endTime;
    private String status;
    private String serviceAgreementStatus;
}
