package ba.unsa.etf.nwt.repository;

import ba.unsa.etf.nwt.entity.ServiceAgreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceAgreementRepository extends JpaRepository<ServiceAgreement, Long> {
}
