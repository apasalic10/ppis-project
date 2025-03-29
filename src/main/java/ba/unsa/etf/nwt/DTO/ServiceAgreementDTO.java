package ba.unsa.etf.nwt.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceAgreementDTO {
    private Long serviceAgreementId;
    private String teacherId;
    private String studentId;
    private Date startDate;
    private Date endDate;
    private String terms;
    private String invoiceNumber;
    private int currentLevel;
    private float amount;
    private List<String> sessionStatuses;
    private Integer ratings;
}
