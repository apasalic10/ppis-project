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
public class InvoiceDTO {
    private Long invoiceId;
    private String invoiceNumber;
    private Date issueDate;
    private Date dueDate;
    private float totalAmount;
    private String status;
    private String serviceAgreementStatus;
}
