package ba.unsa.etf.nwt.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

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

    private String name;
    private String description;
    private Integer sortOrder;

    @ManyToMany(mappedBy = "skillLevels")
    private Set<Listing> listings;
}
