package ba.unsa.etf.nwt.DTO;

import ba.unsa.etf.nwt.entity.Listing;
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

    public ListingDTO(Listing listing) {
        if (listing != null) {
            this.listingId = listing.getId();
            this.title = listing.getTitle();
            this.description = listing.getDescription();
            this.creationDate = listing.getCreationDate();
            this.lastUpdated = listing.getLastUpdated();
            this.status = listing.getStatus();
            this.price = listing.getPrice();
            this.pricingModel = listing.getPricingModel();
            this.viewCount = listing.getViewCount();
            this.featured = listing.isFeatured();
        }
    }
}
