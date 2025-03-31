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
public class PaymentPostDTO {
    private Long serviceAgreementId;
    private Long sessionId;
    private float amount;
    private String currency;
    private Date paymentDate;
    private String paymentMethod;
    private String status;
    private String transactionId;
    private float platformFee;
    private float teacherAmount;
}
