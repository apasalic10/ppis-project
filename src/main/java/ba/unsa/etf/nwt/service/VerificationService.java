package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.entity.Teacher;
import ba.unsa.etf.nwt.entity.Verification;
import ba.unsa.etf.nwt.exception.ResourceNotFoundException;
import ba.unsa.etf.nwt.repository.VerificationRepository;
import org.springframework.stereotype.Service;

@Service
public class VerificationService {
    private final VerificationRepository verificationRepository;

    public VerificationService(VerificationRepository verificationRepository) {
        this.verificationRepository = verificationRepository;
    }

    public Verification getTeacherVerification(Teacher teacher) {
        return verificationRepository.findByTeacher(teacher)
                .orElseThrow(() -> new ResourceNotFoundException("Verification for teacher" + teacher.getFirstName() + " not found!"));
    }
}
