package ba.unsa.etf.nwt.DTO;

import ba.unsa.etf.nwt.entity.Category;
import ba.unsa.etf.nwt.entity.Listing;
import ba.unsa.etf.nwt.entity.ListingCategory;
import ba.unsa.etf.nwt.entity.SkillLevel;
import ba.unsa.etf.nwt.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ListingPostDTO {
    private String title;
    private String description;
    private String status;
    private Float price;
    private String pricingModel;
    private boolean featured;

    private List<TagPostDTO> tags;
    private List<SkillLevel> skillLevels;
    private List<CategoryPostDTO> categories;
}
