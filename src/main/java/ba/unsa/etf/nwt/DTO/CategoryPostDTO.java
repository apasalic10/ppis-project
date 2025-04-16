package ba.unsa.etf.nwt.DTO;

import ba.unsa.etf.nwt.entity.Category;
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

    private Category parentCategory;
    private List<CategoryPostDTO> subcategory;
}
