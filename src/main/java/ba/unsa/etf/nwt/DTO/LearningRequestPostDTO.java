package ba.unsa.etf.nwt.DTO;

import ba.unsa.etf.nwt.entity.LearningRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LearningRequestPostDTO extends ListingPostDTO{
    private String learningGoal;
    private String preferredApproach;
    private String availabilityWindow;
    private Integer urgencyLevel;
    private boolean groupLearningOk;

    public LearningRequestPostDTO(LearningRequest learningRequest) {
        super(learningRequest);

        this.learningGoal = learningRequest.getLearningGoal();
        this.preferredApproach = learningRequest.getPreferredApproach();
        this.availabilityWindow = learningRequest.getAvailabilityWindow();
        this.urgencyLevel = learningRequest.getUrgencyLevel();
        this.groupLearningOk = learningRequest.isGroupLearningOk();
    }
}
