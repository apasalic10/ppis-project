package ba.unsa.etf.nwt.DTO;

import ba.unsa.etf.nwt.entity.SkillLevel;
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
public class SkillLevelDTO {
    private String name;
    private String description;
    private Integer sortOrder;

    public SkillLevelDTO(SkillLevel skillLevel) {
        this.name = skillLevel.getName();
        this.description = skillLevel.getDescription();
        this.sortOrder = skillLevel.getSortOrder();
    }
}
