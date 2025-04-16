package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.DTO.TagPostDTO;
import ba.unsa.etf.nwt.entity.Tag;
import ba.unsa.etf.nwt.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag createTag(TagPostDTO tagPostDTO) {
        Tag tag = new Tag();
        tag.setName(tagPostDTO.getName());
        tag.setUsageCount(tagPostDTO.getUsageCount());

        return tagRepository.save(tag);
    }

    public Tag getTagById(Long id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag with id " + id + " not found"));
    }

    public Iterable<Tag> getAllTags() {
        return tagRepository.findAll();
    }
}
