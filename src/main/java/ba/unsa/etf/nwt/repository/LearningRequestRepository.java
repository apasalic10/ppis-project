package ba.unsa.etf.nwt.repository;

import ba.unsa.etf.nwt.entity.LearningRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearningRequestRepository extends JpaRepository<LearningRequest, Long> {
}
