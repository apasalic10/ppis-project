package ba.unsa.etf.nwt.controller;

import ba.unsa.etf.nwt.DTO.LearningProgressDTO;
import ba.unsa.etf.nwt.repository.LearningProgressRepository;
import ba.unsa.etf.nwt.service.LearningProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

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
}
