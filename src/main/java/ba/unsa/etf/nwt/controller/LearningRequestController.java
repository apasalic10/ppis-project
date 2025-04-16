package ba.unsa.etf.nwt.controller;

import ba.unsa.etf.nwt.DTO.LearningRequestPostDTO;
import ba.unsa.etf.nwt.entity.LearningRequest;
import ba.unsa.etf.nwt.service.LearningRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/learning-requests")
public class LearningRequestController {

    private final LearningRequestService learningRequestService;

    @Autowired
    public LearningRequestController(LearningRequestService learningRequestService) {
        this.learningRequestService = learningRequestService;
    }

    @PostMapping
    public ResponseEntity<LearningRequest> createLearningRequest(@Valid @RequestBody LearningRequestPostDTO dto) {
        try {
            LearningRequest learningRequest = learningRequestService.createLearningRequest(dto);
            return new ResponseEntity<>(learningRequest, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
