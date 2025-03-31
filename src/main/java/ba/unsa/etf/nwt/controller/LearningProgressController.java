package ba.unsa.etf.nwt.controller;

import ba.unsa.etf.nwt.DTO.LearningProgressDTO;
import ba.unsa.etf.nwt.DTO.LearningProgressPostDTO;
import ba.unsa.etf.nwt.DTO.ServiceAgreementDTO;
import ba.unsa.etf.nwt.repository.LearningProgressRepository;
import ba.unsa.etf.nwt.service.LearningProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/learningProgress")
public class LearningProgressController {
    private final LearningProgressService learningProgressService;
    private final LearningProgressRepository learningProgressRepository;

    @Autowired
    public LearningProgressController(LearningProgressService learningProgressService, LearningProgressRepository learningProgressRepository) {
        this.learningProgressService = learningProgressService;
        this.learningProgressRepository = learningProgressRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getLearningProgressById(@PathVariable long id) {
        try{
            LearningProgressDTO dto =  learningProgressService.getLearningProgressDTOById(id);
            return ResponseEntity.ok().body(dto);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Error message", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Object> createLearningProgress(@RequestBody LearningProgressPostDTO dto) {
        try {
            String createdProgress = learningProgressService.createLearningProgress(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProgress);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("Error message", e.getMessage()));
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateLearningProgressField(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {
        try {
            LearningProgressDTO updatedDTO = learningProgressService.updateLearningProgressField(id, updates);
            return ResponseEntity.ok(updatedDTO);
        }
        catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("Error message", ex.getMessage()));
        }
        catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("Error message", ex.getMessage()));
        }
    }
}
