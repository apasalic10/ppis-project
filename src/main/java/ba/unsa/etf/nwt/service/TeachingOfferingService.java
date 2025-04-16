package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.DTO.TeachingOfferingPostDTO;
import ba.unsa.etf.nwt.entity.TeachingOffering;
import ba.unsa.etf.nwt.repository.ListingRepository;
import ba.unsa.etf.nwt.util.ListingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeachingOfferingService {

    private final ListingRepository listingRepository;
    private final ListingUtils listingUtils;

    @Autowired
    public TeachingOfferingService(ListingRepository listingRepository,
                                   ListingUtils listingUtils) {
        this.listingRepository = listingRepository;
        this.listingUtils = listingUtils;
    }

    public TeachingOffering createTeachingOffering(TeachingOfferingPostDTO dto) {
        TeachingOffering offering = new TeachingOffering();

        listingUtils.setCommonFields(offering, dto);

        offering.setTeachingApproach(dto.getTeachingApproach());
        offering.setMaxStudents(dto.getMaxStudents());
        offering.setPrerequisites(dto.getPrerequisites());
        offering.setLearningOutcomes(dto.getLearningOutcomes());
        offering.setMaterials(dto.getMaterials());
        offering.setDurationMinutes(dto.getDurationMinutes());
        offering.setGroupSession(dto.isGroupSession());

        listingUtils.setTagsCategoriesSkillLevels(offering, dto);

        listingRepository.save(offering);
        return offering;
    }
}
