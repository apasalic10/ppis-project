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
public class ListingPostDTO {
    private String title;
    private String description;
    private String status;
    private Float price;
    private String pricingModel;
    private boolean featured;

    private List<Long> tagIds;
    private List<Long> skillLevelIds;
    private List<Long> categoryIds;
}
