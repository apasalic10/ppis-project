package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.DTO.LearningProgressDTO;
import ba.unsa.etf.nwt.DTO.LearningProgressPostDTO;
import ba.unsa.etf.nwt.DTO.ServiceAgreementDTO;
import ba.unsa.etf.nwt.entity.LearningProgress;
import ba.unsa.etf.nwt.entity.ServiceAgreement;
import ba.unsa.etf.nwt.repository.LearningProgressRepository;
import ba.unsa.etf.nwt.repository.ServiceAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class LearningProgressService {
    public final LearningProgressRepository learningProgressRepository;
    public final ServiceAgreementRepository serviceAgreementRepository;

    @Autowired
    public LearningProgressService(LearningProgressRepository learningProgressRepository ,ServiceAgreementRepository serviceAgreementRepository) {
        this.learningProgressRepository = learningProgressRepository;
        this.serviceAgreementRepository = serviceAgreementRepository;
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

    public String createLearningProgress(LearningProgressPostDTO dto) {
        // Validate if Service Agreement exists
        ServiceAgreement serviceAgreement = serviceAgreementRepository.findById(dto.getServiceAgreementId())
                .orElseThrow(() -> new RuntimeException("Service Agreement not found"));

        if(dto.getStudentId() == null || dto.getSkillName() == null || dto.getStudentNotes() == null || dto.getTeacherFeedback() == null) {
            throw new RuntimeException("Learning Progress has missing parameters");
        }

        // Convert DTO to Entity
        LearningProgress learningProgress = new LearningProgress();
        learningProgress.setUuid(UUID.randomUUID().toString());
        learningProgress.setServiceAgreement(serviceAgreement);
        learningProgress.setStudentId(dto.getStudentId());
        if (!StringUtils.hasText(dto.getSkillName()))
            throw new RuntimeException("Learning Progress has missing skill name");
        learningProgress.setSkillName(dto.getSkillName());
        if (dto.getInitialLevel() > dto.getCurrentLevel())
            throw new RuntimeException("Initial level should be less than current level");
        learningProgress.setInitialLevel(dto.getInitialLevel());
        learningProgress.setCurrentLevel(dto.getCurrentLevel());
        learningProgress.setTeacherFeedback(dto.getTeacherFeedback());
        learningProgress.setStudentNotes(dto.getStudentNotes());
        learningProgress.setAssessmentDate(dto.getAssessmentDate());

        // Save to database
        learningProgressRepository.save(learningProgress);
        return "LearningProgress created";
    }

    public LearningProgressDTO updateLearningProgressField(Long id, Map<String, Object> updates) {
        LearningProgress lp = learningProgressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Learning progress with ID " + id + " not found"));

        // Loop through the provided updates and set the fields dynamically
        updates.forEach((key, value) -> {
            switch (key) {
                case "currentLevel" -> lp.setCurrentLevel(Integer.parseInt(value.toString()));
                case "teacherFeedback" -> lp.setTeacherFeedback(value.toString());
                case "studentNotes" -> lp.setStudentNotes(value.toString());
                case "assessmentDate" -> lp.setAssessmentDate(Date.from(Instant.parse(value.toString())));
                default -> throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        // Save updated entity
        LearningProgress updatedLP = learningProgressRepository.save(lp);

        return convertToDTO(updatedLP);
    }
}
