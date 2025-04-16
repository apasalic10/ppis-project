package ba.unsa.etf.nwt.controller;

import ba.unsa.etf.nwt.DTO.TagPostDTO;
import ba.unsa.etf.nwt.entity.Tag;
import ba.unsa.etf.nwt.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping("/")
    public ResponseEntity<Tag> createTag(@Valid @RequestBody TagPostDTO tagPostDTO) {
        try {
            Tag tag = tagService.createTag(tagPostDTO);
            return new ResponseEntity<>(tag, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable("id") Long id) {
        try {
            Tag tag = tagService.getTagById(id);
            return new ResponseEntity<>(tag, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity<Iterable<Tag>> getAllTags() {
        try {
            Iterable<Tag> tags = tagService.getAllTags();
            return new ResponseEntity<>(tags, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
