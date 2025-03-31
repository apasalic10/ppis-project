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
public class InvoicePostDTO {
    private Long serviceAgreementId;
    private String invoiceNumber;
    private Date issueDate;
    private Date dueDate;
    private float totalAmount;
    private String status;
}
