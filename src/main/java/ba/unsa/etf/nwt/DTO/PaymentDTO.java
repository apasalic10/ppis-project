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
public class PaymentDTO {
    private Long paymentId;
    private float amount;
    private String currency;
    private Date paymentDate;
    private String status;
    private String serviceAgreementStatus;
    private String sessionStatus;
}
