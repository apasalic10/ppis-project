package ba.unsa.etf.nwt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name="learning_requests")
@PrimaryKeyJoinColumn(name = "listing_id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class LearningRequest extends Listing {
    private String learningGoal;
    private String preferredApproach;
    private String availabilityWindow;
    private Integer urgencyLevel;
    private boolean groupLearningOk;
}
