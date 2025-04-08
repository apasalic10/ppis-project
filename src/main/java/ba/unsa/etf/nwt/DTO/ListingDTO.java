package ba.unsa.etf.nwt.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ListingDTO {
    private Long listingId;
    private String title;
    private String description;
    private Date creationDate;
    private Date lastUpdated;
    private String status;
    private Float price;
    private String pricingModel;
    private Integer viewCount;
    private boolean featured;
}
