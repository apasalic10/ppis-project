package ba.unsa.etf.nwt.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

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
}
