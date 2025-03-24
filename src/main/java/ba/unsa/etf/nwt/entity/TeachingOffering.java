package ba.unsa.etf.nwt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name="teaching_offerings")
@PrimaryKeyJoinColumn(name = "listing_id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class TeachingOffering extends Listing {
    private String teachingApproach;
    private Integer maxStudents;
    private String prerequisites;
    private String learningOutcomes;
    private String materials;
    private Integer durationMinutes;
    private boolean groupSession;

}
