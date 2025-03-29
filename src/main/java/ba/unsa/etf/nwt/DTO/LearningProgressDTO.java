package ba.unsa.etf.nwt.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LearningProgressDTO {
    public Long learningProgressId;
    public String studentId;
    private String skillName;
    private int currentLevel;
    private String serviceAgreementStatus;
}
