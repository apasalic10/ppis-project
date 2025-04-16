package ba.unsa.etf.nwt.DTO;

import ba.unsa.etf.nwt.entity.Tag;
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
public class TagPostDTO {
    private String name;
    private Integer usageCount;

    public TagPostDTO(Tag tag) {
        this.name = tag.getName();
        this.usageCount = tag.getUsageCount();
    }
}
