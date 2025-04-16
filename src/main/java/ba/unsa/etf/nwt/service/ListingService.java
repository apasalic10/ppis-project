package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.DTO.CategoryPostDTO;
import ba.unsa.etf.nwt.DTO.LearningRequestPostDTO;
import ba.unsa.etf.nwt.DTO.ListingDTO;
import ba.unsa.etf.nwt.DTO.ListingPostDTO;
import ba.unsa.etf.nwt.DTO.TagPostDTO;
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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListingService {
    private final ListingRepository listingRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final SkillLevelRepository skillLevelRepository;

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

    @Autowired
    private ModelMapper modelMapper;


    public ListingDTO getListingDTOById(Long id) {
        Listing listing = listingRepository.findById(id).orElseThrow(() -> new RuntimeException("Listing with id " + id + " not found"));
        return modelMapper.map(listing, ListingDTO.class);
    }

    public Page<ListingDTO> getAllListings(Pageable pageable) {
        Page<Listing> listingsPage = listingRepository.findAll(pageable);

        return listingsPage.map(listing -> modelMapper.map(listing, ListingDTO.class));
    }

    public List<ListingDTO> filterListings(String status, Float minPrice, Float maxPrice, String title) {
        List<Listing> listings = listingRepository.filterListings(status, minPrice, maxPrice, title);
        return listings.stream()
                .map(listing -> modelMapper.map(listing, ListingDTO.class))
                .collect(Collectors.toList());
    }
}
