package ba.unsa.etf.nwt.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "teaching_offerings")
@PrimaryKeyJoinColumn(name = "listing_id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class TeachingOffering extends Listing {

    @Size(max = 1000, message = "Teaching approach must not exceed 1000 characters")
    private String teachingApproach;

    @Min(value = 1, message = "Maximum number of students must be at least 1")
    private Integer maxStudents;

    @Size(max = 1000, message = "Prerequisites must not exceed 1000 characters")
    private String prerequisites;

    @Size(max = 1000, message = "Learning outcomes must not exceed 1000 characters")
    private String learningOutcomes;

    @Size(max = 1000, message = "Materials description must not exceed 1000 characters")
    private String materials;

    @Min(value = 5, message = "Duration must be at least 5 minutes")
    private Integer durationMinutes;

    private boolean groupSession;
}

