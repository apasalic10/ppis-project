package ba.unsa.etf.nwt.repository;

import ba.unsa.etf.nwt.entity.LearningProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningProgressRepository extends JpaRepository<LearningProgress, Long> {
}
