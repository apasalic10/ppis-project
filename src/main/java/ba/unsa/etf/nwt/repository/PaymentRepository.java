package ba.unsa.etf.nwt.repository;

import ba.unsa.etf.nwt.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
