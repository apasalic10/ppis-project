package ba.unsa.etf.nwt.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="teaching_offerings")
@PrimaryKeyJoinColumn(name = "listing_id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class TeachingOffering extends Listing {
    private String teachingApproach;
    private Integer maxStudents;
    private String prerequisites;
    private String learningOutcomes;
    private String materials;
    private Integer durationMinutes;
    private boolean groupSession;

    @PostLoad
    public void postLoad() {
        this.setType("teaching_offering");  // Postavi vrednost za 'type'
    }
}
