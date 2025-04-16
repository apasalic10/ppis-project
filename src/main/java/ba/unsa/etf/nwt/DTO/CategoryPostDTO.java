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

    public CategoryPostDTO(Category category) {
        this.name = category.getName();
        this.description = category.getDescription();
        this.iconUrl = category.getIconUrl();
        this.sortOrder = category.getSortOrder();

        this.parentCategory = category.getParentCategory();

        this.subcategory = category.getSubcategories() != null
                ? category.getSubcategories().stream()
                .map(CategoryPostDTO::new)
                .toList()
                : null;
    }
}
