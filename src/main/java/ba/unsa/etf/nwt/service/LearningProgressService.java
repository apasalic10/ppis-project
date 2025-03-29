package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.DTO.LearningProgressDTO;
import ba.unsa.etf.nwt.entity.LearningProgress;
import ba.unsa.etf.nwt.repository.LearningProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LearningProgressService {
    public final LearningProgressRepository learningProgressRepository;

    @Autowired
    public LearningProgressService(LearningProgressRepository learningProgressRepository) {
        this.learningProgressRepository = learningProgressRepository;
    }

    private LearningProgressDTO convertToDTO(LearningProgress lp) {

        return new LearningProgressDTO(
                lp.getId(),
                lp.getStudentId(),
                lp.getSkillName(),
                lp.getCurrentLevel(),
                (lp.getServiceAgreement() != null) ? lp.getServiceAgreement().getStatus(): null
        );
    }

    public LearningProgressDTO getLearningProgressDTOById(long id) {
        LearningProgress lp = learningProgressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("LearningProgress not found with ID: " + id));
        return convertToDTO(lp);
    }
}
