package ba.unsa.etf.nwt.util;

import ba.unsa.etf.nwt.DTO.CategoryPostDTO;
import ba.unsa.etf.nwt.DTO.ListingPostDTO;
import ba.unsa.etf.nwt.DTO.TagPostDTO;
import ba.unsa.etf.nwt.entity.*;
import ba.unsa.etf.nwt.repository.CategoryRepository;
import ba.unsa.etf.nwt.repository.SkillLevelRepository;
import ba.unsa.etf.nwt.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ListingUtils {

    private final TagRepository tagRepository;
    private final SkillLevelRepository skillLevelRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ListingUtils(TagRepository tagRepository,
                        SkillLevelRepository skillLevelRepository,
                        CategoryRepository categoryRepository) {
        this.tagRepository = tagRepository;
        this.skillLevelRepository = skillLevelRepository;
        this.categoryRepository = categoryRepository;
    }

    public void setCommonFields(Listing listing, ListingPostDTO dto) {
        listing.setTitle(dto.getTitle());
        listing.setDescription(dto.getDescription());
        listing.setStatus(dto.getStatus());
        listing.setPrice(dto.getPrice());
        listing.setPricingModel(dto.getPricingModel());
        listing.setFeatured(dto.isFeatured());
        listing.setCreationDate(new Date());
        listing.setLastUpdated(new Date());
    }

    public void setTagsCategoriesSkillLevels(Listing listing, ListingPostDTO dto) {
        if (dto.getTags() != null) {
            List<Tag> tags = new ArrayList<>();
            for (TagPostDTO tagDto : dto.getTags()) {
                Tag tag = tagRepository.findByName(tagDto.getName())
                        .orElseThrow(() -> new RuntimeException("Tag with name " + tagDto.getName() + " not found"));
                tags.add(tag);
            }
            listing.setTags(tags);
        }

        if (dto.getSkillLevels() != null) {
            List<SkillLevel> skillLevels = new ArrayList<>();
            for (SkillLevel skillLevelDto : dto.getSkillLevels()) {
                SkillLevel skillLevel = skillLevelRepository.findByName(skillLevelDto.getName())
                        .orElseThrow(() -> new RuntimeException("Skill Level with name " + skillLevelDto.getName() + " not found"));
                skillLevels.add(skillLevel);
            }
            listing.setSkillLevels(skillLevels);
        }

        if (dto.getCategories() != null) {
            List<ListingCategory> listingCategories = new ArrayList<>();
            for (CategoryPostDTO categoryDto : dto.getCategories()) {
                Category category = categoryRepository.findByName(categoryDto.getName())
                        .orElseThrow(() -> new RuntimeException("Category with name " + categoryDto.getName() + " not found"));

                ListingCategory lc = new ListingCategory();
                lc.setCategory(category);
                lc.setListing(listing);
                lc.setAssignedDate(new Date());
                listingCategories.add(lc);
            }
            listing.setListingCategories(listingCategories);
        }
    }
}
