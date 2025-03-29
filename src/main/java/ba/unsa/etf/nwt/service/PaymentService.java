package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.DTO.PaymentDTO;
import ba.unsa.etf.nwt.entity.Payment;
import ba.unsa.etf.nwt.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    private PaymentDTO convertToDTO(Payment payment) {
        return new PaymentDTO(
                payment.getId(),
                payment.getAmount(),
                payment.getCurrency(),
                payment.getPaymentDate(),
                payment.getStatus(),
                (payment.getServiceAgreement() != null) ? payment.getServiceAgreement().getStatus() : null,
                (payment.getSession() != null) ? payment.getSession().getStatus() : null
        );
    }

    public PaymentDTO getPaymentDTOById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with ID: " + id));
        return convertToDTO(payment);
    }
}
