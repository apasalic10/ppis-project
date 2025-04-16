package ba.unsa.etf.nwt.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "learning_requests")
@PrimaryKeyJoinColumn(name = "listing_id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class LearningRequest extends Listing {

    @NotBlank(message = "Learning goal must not be blank")
    @Size(max = 1000, message = "Learning goal must not exceed 1000 characters")
    private String learningGoal;

    @Size(max = 1000, message = "Preferred approach must not exceed 1000 characters")
    private String preferredApproach;

    @Size(max = 255, message = "Availability window must not exceed 255 characters")
    private String availabilityWindow;

    @Min(value = 0, message = "Urgency level must be zero or greater")
    @Max(value = 10, message = "Urgency level must not exceed 10")
    private Integer urgencyLevel;

    private boolean groupLearningOk;
}
