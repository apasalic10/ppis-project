package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.DTO.ReviewDTO;
import ba.unsa.etf.nwt.entity.Review;
import ba.unsa.etf.nwt.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    private ReviewDTO convertToDTO(Review review) {
        return new ReviewDTO(
                review.getId(),
                review.getReviewerId(),
                review.getRevieweeId(),
                review.getRating(),
                review.getComment(),
                (review.getServiceAgreement() != null) ? review.getServiceAgreement().getStatus() : null,
                (review.getSession() != null) ? review.getSession().getStatus() : null
        );
    }

    public ReviewDTO getReviewDTOById(long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with ID: " + id));
        return convertToDTO(review);
    }
}
