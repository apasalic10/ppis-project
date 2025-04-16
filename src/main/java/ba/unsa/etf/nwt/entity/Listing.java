package ba.unsa.etf.nwt.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "listings")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = LearningRequest.class, name = "learning_request"),
        @JsonSubTypes.Type(value = TeachingOffering.class, name = "teaching_offering")
})
public abstract class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
    @NotNull(message = "UUID must not be null")
    private String uuid;

    @NotBlank(message = "Title must not be blank")
    @Size(max = 255, message = "Title must not exceed 255 characters")
    private String title;

    @Size(max = 5000, message = "Description must not exceed 5000 characters")
    private String description;

    @PastOrPresent(message = "Creation date must be in the past or present")
    private Date creationDate;

    @PastOrPresent(message = "Last updated date must be in the past or present")
    private Date lastUpdated;

    @Pattern(regexp = "^(active|inactive|archived)?$", message = "Status must be 'active', 'inactive' or 'archived'")
    private String status;

    @PositiveOrZero(message = "Price must be zero or positive")
    private Float price;

    @Size(max = 100, message = "Pricing model must not exceed 100 characters")
    private String pricingModel;

    @Min(value = 0, message = "View count must be zero or greater")
    private Integer viewCount;

    private boolean featured;

    @JsonManagedReference
    @ManyToMany
    @JoinTable(
            name = "listing_tags",
            joinColumns = @JoinColumn(name = "listing_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<@NotNull Tag> tags;

    @JsonManagedReference
    @ManyToMany
    @JoinTable(
            name = "listing_skill_levels",
            joinColumns = @JoinColumn(name = "listing_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_level_id")
    )
    private List<@NotNull SkillLevel> skillLevels;

    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<@Valid ListingCategory> listingCategories = new ArrayList<>();

    @NotBlank(message = "Type must be specified (e.g., 'learning_request' or 'teaching_offering')")
    private String type;


    @PrePersist
    public void prePersist() {
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID().toString();
        }
    }
}
