package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.DTO.PaymentDTO;
import ba.unsa.etf.nwt.DTO.PaymentPostDTO;
import ba.unsa.etf.nwt.DTO.ServiceAgreementDTO;
import ba.unsa.etf.nwt.entity.Payment;
import ba.unsa.etf.nwt.entity.ServiceAgreement;
import ba.unsa.etf.nwt.entity.Session;
import ba.unsa.etf.nwt.repository.PaymentRepository;
import ba.unsa.etf.nwt.repository.ServiceAgreementRepository;
import ba.unsa.etf.nwt.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentService {
    public final PaymentRepository paymentRepository;
    public final ServiceAgreementRepository serviceAgreementRepository;
    public final SessionRepository sessionRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, ServiceAgreementRepository serviceAgreementRepository, SessionRepository sessionRepository) {
        this.paymentRepository = paymentRepository;
        this.serviceAgreementRepository = serviceAgreementRepository;
        this.sessionRepository = sessionRepository;
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

    public String createPayment(PaymentPostDTO dto) {
        // Validate if Service Agreement exists
        ServiceAgreement serviceAgreement = serviceAgreementRepository.findById(dto.getServiceAgreementId())
                .orElseThrow(() -> new RuntimeException("Service Agreement not found"));

        // Validate if Session exists
        Session session = sessionRepository.findById(dto.getSessionId())
                .orElseThrow(() -> new RuntimeException("Session not found"));

        if(dto.getCurrency() == null || dto.getPaymentDate() == null || dto.getPaymentMethod() == null|| dto.getStatus() == null || dto.getTransactionId() == null) {
            throw new RuntimeException("Invalid payment data");
        }

        // Convert DTO to Entity
        Payment payment = new Payment();
        payment.setUuid(UUID.randomUUID().toString()); // Generate a unique UUID
        payment.setServiceAgreement(serviceAgreement);
        payment.setSession(session);
        if (dto.getAmount() < 0)
            throw new RuntimeException("Amount can't be negative");
        payment.setAmount(dto.getAmount());
        if (!StringUtils.hasText(dto.getCurrency()))
            throw new RuntimeException("Payment has missing currency");
        payment.setCurrency(dto.getCurrency());
        payment.setPaymentDate(dto.getPaymentDate());
        if (!StringUtils.hasText(dto.getPaymentMethod()))
            throw new RuntimeException("Payment has missing payment method");
        payment.setPaymentMethod(dto.getPaymentMethod());
        if (!StringUtils.hasText(dto.getStatus()))
            throw new RuntimeException("Payment has missing status");
        payment.setStatus(dto.getStatus());
        payment.setTransactionId(dto.getTransactionId());
        if (dto.getPlatformFee() < 0)
            throw new RuntimeException("Platform fee can't be negative");
        payment.setPlatformFee(dto.getPlatformFee());
        if (dto.getTeacherAmount() < 0)
            throw new RuntimeException("Teacher amount can't be negative");
        payment.setTeacherAmount(dto.getTeacherAmount());

        // Save to database
        return "Payment created";
    }

    public PaymentDTO updatePaymentField(Long id, Map<String, Object> updates) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment with ID " + id + " not found"));

        // Loop through the provided updates and set the fields dynamically
        updates.forEach((key, value) -> {
            if (key.equals("status")) {
                payment.setStatus(value.toString());
            } else {
                throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        // Save updated entity
        Payment updatedPayment = paymentRepository.save(payment);

        return convertToDTO(updatedPayment);
    }
}
