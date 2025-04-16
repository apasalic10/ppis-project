package ba.unsa.etf.nwt.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "skill_levels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SkillLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Skill level name must not be blank")
    @Size(max = 255, message = "Skill level name must not exceed 255 characters")
    private String name;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;

    @Min(value = 0, message = "Sort order must be zero or greater")
    private Integer sortOrder;

    @ManyToMany(mappedBy = "skillLevels")
    @JsonBackReference
    private List<Listing> listings;
}

