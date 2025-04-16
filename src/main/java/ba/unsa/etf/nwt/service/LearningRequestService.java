package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.DTO.LearningRequestPostDTO;
import ba.unsa.etf.nwt.entity.LearningRequest;
import ba.unsa.etf.nwt.repository.ListingRepository;
import ba.unsa.etf.nwt.util.ListingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LearningRequestService {

    private final ListingRepository listingRepository;
    private final ListingUtils listingUtils;

    @Autowired
    public LearningRequestService(ListingRepository listingRepository,
                                  ListingUtils listingUtils) {
        this.listingRepository = listingRepository;
        this.listingUtils = listingUtils;
    }

    public LearningRequest createLearningRequest(LearningRequestPostDTO dto) {
        LearningRequest request = new LearningRequest();

        listingUtils.setCommonFields(request, dto);

        request.setLearningGoal(dto.getLearningGoal());
        request.setPreferredApproach(dto.getPreferredApproach());
        request.setAvailabilityWindow(dto.getAvailabilityWindow());
        request.setUrgencyLevel(dto.getUrgencyLevel());
        request.setGroupLearningOk(dto.isGroupLearningOk());

        listingUtils.setTagsCategoriesSkillLevels(request, dto);

        listingRepository.save(request);
        return request;
    }
}
