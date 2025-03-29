package ba.unsa.etf.nwt.controller;

import ba.unsa.etf.nwt.DTO.ReviewDTO;
import ba.unsa.etf.nwt.repository.ReviewRepository;
import ba.unsa.etf.nwt.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    private final ReviewRepository reviewRepository;
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewRepository reviewRepository, ReviewService reviewService) {
        this.reviewRepository = reviewRepository;
        this.reviewService = reviewService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findReviewById(@PathVariable Long id) {
        try{
            ReviewDTO dto = reviewService.getReviewDTOById(id);
            return ResponseEntity.ok().body(dto);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Error message", e.getMessage()));
        }
    }
}
