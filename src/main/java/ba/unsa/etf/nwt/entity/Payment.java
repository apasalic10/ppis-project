package ba.unsa.etf.nwt.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
    private String uuid;

    @OneToOne
    @JoinColumn(name = "service_agreement_id", nullable = false, unique = true)
    private ServiceAgreement serviceAgreement;

    @OneToOne
    @JoinColumn(name = "session_id", nullable = false, unique = true)
    private Session session;

    private float amount;
    private String currency;
    private Date paymentDate;
    private String paymentMethod;
    private String status;
    private String transactionId;
    private float platformFee;
    private float teacherAmount;
}
