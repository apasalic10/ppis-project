package ba.unsa.etf.nwt.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "listing_categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ListingCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "listing_id", nullable = false)
    @NotNull(message = "Listing must not be null")
    @JsonBackReference
    private Listing listing;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @NotNull(message = "Category must not be null")
    private Category category;

    @PastOrPresent(message = "Assigned date must be in the past or present")
    private Date assignedDate;
}

