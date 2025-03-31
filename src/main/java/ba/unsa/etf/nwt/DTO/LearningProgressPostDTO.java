package ba.unsa.etf.nwt.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LearningProgressPostDTO {
    private Long serviceAgreementId;
    private String studentId;
    private String skillName;
    private int initialLevel;
    private int currentLevel;
    private String teacherFeedback;
    private String studentNotes;
    private Date assessmentDate;
}
