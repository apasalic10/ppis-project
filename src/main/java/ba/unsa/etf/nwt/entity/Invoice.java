package ba.unsa.etf.nwt.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "invoice")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
    private String uuid;

    @OneToOne
    @JoinColumn(name = "service_agreement_id", nullable = false, unique = true)
    private ServiceAgreement serviceAgreement;

    private String invoiceNumber;
    private Date issueDate;
    private Date dueDate;
    private float totalAmount;
    private String status;

    @Lob
    private byte[] pdfDocument;
}
