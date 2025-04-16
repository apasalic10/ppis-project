package ba.unsa.etf.nwt.DTO;

import ba.unsa.etf.nwt.entity.TeachingOffering;
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
public class TeachingOfferingPostDTO extends ListingPostDTO {
    private String teachingApproach;
    private Integer maxStudents;
    private String prerequisites;
    private String learningOutcomes;
    private String materials;
    private Integer durationMinutes;
    private boolean groupSession;

    public TeachingOfferingPostDTO(TeachingOffering teachingOffering) {
        super(teachingOffering);

        this.teachingApproach = teachingOffering.getTeachingApproach();
        this.maxStudents = teachingOffering.getMaxStudents();
        this.prerequisites = teachingOffering.getPrerequisites();
        this.learningOutcomes = teachingOffering.getLearningOutcomes();
        this.materials = teachingOffering.getMaterials();
        this.durationMinutes = teachingOffering.getDurationMinutes();
        this.groupSession = teachingOffering.isGroupSession();
    }
}
