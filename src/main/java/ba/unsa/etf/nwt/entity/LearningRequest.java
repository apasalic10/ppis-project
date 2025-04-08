package ba.unsa.etf.nwt.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.PostLoad;
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
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class LearningRequest extends Listing {
    private String learningGoal;
    private String preferredApproach;
    private String availabilityWindow;
    private Integer urgencyLevel;
    private boolean groupLearningOk;

    @PostLoad
    public void postLoad() {
        this.setType("learning_request");
    }
}
