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
public class TeachingOfferingPostDTO extends ListingPostDTO {
    private String teachingApproach;
    private Integer maxStudents;
    private String prerequisites;
    private String learningOutcomes;
    private String materials;
    private Integer durationMinutes;
    private boolean groupSession;
}
