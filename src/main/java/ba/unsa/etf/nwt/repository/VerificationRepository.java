package ba.unsa.etf.nwt.repository;


import ba.unsa.etf.nwt.entity.Teacher;
import ba.unsa.etf.nwt.entity.Verification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationRepository extends JpaRepository<Verification, Long> {
    Optional<Verification> findByTeacher(Teacher teacher);
}
