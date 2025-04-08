package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.DTO.LearningRequestPostDTO;
import ba.unsa.etf.nwt.DTO.ListingDTO;
import ba.unsa.etf.nwt.DTO.ListingPostDTO;
import ba.unsa.etf.nwt.DTO.TeachingOfferingPostDTO;
import ba.unsa.etf.nwt.entity.Category;
import ba.unsa.etf.nwt.entity.LearningRequest;
import ba.unsa.etf.nwt.entity.Listing;
import ba.unsa.etf.nwt.entity.ListingCategory;
import ba.unsa.etf.nwt.entity.SkillLevel;
import ba.unsa.etf.nwt.entity.Tag;
import ba.unsa.etf.nwt.entity.TeachingOffering;
import ba.unsa.etf.nwt.repository.CategoryRepository;
import ba.unsa.etf.nwt.repository.ListingRepository;
import ba.unsa.etf.nwt.repository.SkillLevelRepository;
import ba.unsa.etf.nwt.repository.TagRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListingService {
    private final ListingRepository listingRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final SkillLevelRepository skillLevelRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public ListingService(ListingRepository listingRepository,
                          CategoryRepository categoryRepository,
                          TagRepository tagRepository,
                          SkillLevelRepository skillLevelRepository) {
        this.listingRepository = listingRepository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
        this.skillLevelRepository = skillLevelRepository;
    }

    public ListingDTO convertToDto(Listing listing) {
        if (listing == null) {
            return null;
        }

        return new ListingDTO(
                listing.getId(), // listingId
                listing.getTitle(),
                listing.getDescription(),
                listing.getCreationDate(),
                listing.getLastUpdated(),
                listing.getStatus(),
                listing.getPrice(),
                listing.getPricingModel(),
                listing.getViewCount(),
                listing.isFeatured()
        );
    }

    public Listing applyPatchToListing(JsonPatch patch, Listing targetListing)
            throws JsonPatchException, Exception {
        JsonNode targetNode = objectMapper.convertValue(targetListing, JsonNode.class);

        JsonNode patchedNode = patch.apply(targetNode);

        return objectMapper.treeToValue(patchedNode, targetListing.getClass());
    }

    public ListingDTO getListingDTOById(Long id) {
        Listing listing = listingRepository.findById(id).orElseThrow(() -> new RuntimeException("Listing with id " + id + " not found"));
        return convertToDto(listing);
    }

    public Listing getListingById(Long id) {
        return listingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Listing with ID " + id + " not found"));
    }

    public Listing save(Listing listing) {
        return listingRepository.save(listing);
    }

    public List<ListingDTO> getAllListings() {
        List<Listing> listings = listingRepository.findAll();
        return listings.stream().map(this::convertToDto).toList();
    }

    public String createTeachingOffering(TeachingOfferingPostDTO teachingOfferingDTO) {
        TeachingOffering teachingOffering = new TeachingOffering();

        setCommonFields(teachingOffering, teachingOfferingDTO);

        teachingOffering.setTeachingApproach(teachingOfferingDTO.getTeachingApproach());
        teachingOffering.setMaxStudents(teachingOfferingDTO.getMaxStudents());
        teachingOffering.setPrerequisites(teachingOfferingDTO.getPrerequisites());
        teachingOffering.setLearningOutcomes(teachingOfferingDTO.getLearningOutcomes());
        teachingOffering.setMaterials(teachingOfferingDTO.getMaterials());
        teachingOffering.setDurationMinutes(teachingOfferingDTO.getDurationMinutes());
        teachingOffering.setGroupSession(teachingOfferingDTO.isGroupSession());

        setTagsAndCategoriesAndSkillLevels(teachingOffering, teachingOfferingDTO);

        teachingOffering.setCreationDate(new java.util.Date());
        teachingOffering.setLastUpdated(new java.util.Date());

        listingRepository.save(teachingOffering);

        return teachingOffering.getUuid();
    }

    public String createLearningRequest(LearningRequestPostDTO learningRequestDTO) {
        LearningRequest learningRequest = new LearningRequest();

        setCommonFields(learningRequest, learningRequestDTO);

        learningRequest.setLearningGoal(learningRequestDTO.getLearningGoal());
        learningRequest.setPreferredApproach(learningRequestDTO.getPreferredApproach());
        learningRequest.setAvailabilityWindow(learningRequestDTO.getAvailabilityWindow());
        learningRequest.setUrgencyLevel(learningRequestDTO.getUrgencyLevel());
        learningRequest.setGroupLearningOk(learningRequestDTO.isGroupLearningOk());

        setTagsAndCategoriesAndSkillLevels(learningRequest, learningRequestDTO);

        learningRequest.setCreationDate(new java.util.Date());
        learningRequest.setLastUpdated(new java.util.Date());

        listingRepository.save(learningRequest);

        return learningRequest.getUuid();
    }

    private void setCommonFields(Listing listing, ListingPostDTO listingPostDTO) {
        listing.setTitle(listingPostDTO.getTitle());
        listing.setDescription(listingPostDTO.getDescription());
        listing.setStatus(listingPostDTO.getStatus());
        listing.setPrice(listingPostDTO.getPrice());
        listing.setPricingModel(listingPostDTO.getPricingModel());
        listing.setFeatured(listingPostDTO.isFeatured());
    }

    private void setTagsAndCategoriesAndSkillLevels(Listing listing, ListingPostDTO listingPostDTO) {

        if (listingPostDTO.getTagIds() != null) {
            List<Tag> tags = new ArrayList<>();
            for (Long tagId : listingPostDTO.getTagIds()) {
                Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new RuntimeException("Tag not found"));
                tags.add(tag);
            }
            listing.setTags(tags);
        }

        if (listingPostDTO.getSkillLevelIds() != null) {
            List<SkillLevel> skillLevels = new ArrayList<>();
            for (Long skillLevelId : listingPostDTO.getSkillLevelIds()) {
                SkillLevel skillLevel = skillLevelRepository.findById(skillLevelId).orElseThrow(() -> new RuntimeException("Skill Level not found"));
                skillLevels.add(skillLevel);
            }
            listing.setSkillLevels(skillLevels);
        }

        if (listingPostDTO.getCategoryIds() != null) {
            List<ListingCategory> listingCategories = new ArrayList<>();
            for (Long categoryId : listingPostDTO.getCategoryIds()) {
                Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));
                ListingCategory listingCategory = new ListingCategory();
                listingCategory.setListing(listing);
                listingCategory.setCategory(category);
                listingCategory.setAssignedDate(new java.util.Date());
                listingCategories.add(listingCategory);
            }
            listing.setListingCategories(listingCategories);
        }
    }
}
