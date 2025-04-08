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
public class CategoryPostDTO {
    private String name;
    private String description;
    private String iconUrl;
    private Integer sortOrder;

    private Long parentCategoryId;
    private List<Long> subcategoryIds;
}
