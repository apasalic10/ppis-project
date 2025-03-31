package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.DTO.ReviewDTO;
import ba.unsa.etf.nwt.DTO.ReviewPostDTO;
import ba.unsa.etf.nwt.DTO.ServiceAgreementDTO;
import ba.unsa.etf.nwt.entity.Review;
import ba.unsa.etf.nwt.entity.ServiceAgreement;
import ba.unsa.etf.nwt.entity.Session;
import ba.unsa.etf.nwt.repository.ReviewRepository;
import ba.unsa.etf.nwt.repository.ServiceAgreementRepository;
import ba.unsa.etf.nwt.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ServiceAgreementRepository serviceAgreementRepository;
    private final SessionRepository sessionRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, ServiceAgreementRepository serviceAgreementRepository, SessionRepository sessionRepository) {
        this.reviewRepository = reviewRepository;
        this.serviceAgreementRepository = serviceAgreementRepository;
        this.sessionRepository = sessionRepository;
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

    public String createReview(ReviewPostDTO dto) {
        // Validate if Service Agreement exists
        ServiceAgreement serviceAgreement = serviceAgreementRepository.findById(dto.getServiceAgreementId())
                .orElseThrow(() -> new RuntimeException("Service Agreement not found"));

        // Validate if Session exists
        Session session = sessionRepository.findById(dto.getSessionId())
                .orElseThrow(() -> new RuntimeException("Session not found"));

        if(dto.getReviewerId() == null || dto.getRevieweeId() == null || dto.getComment() == null || dto.getSubmissionDate() == null) {
            throw new RuntimeException("Review has missing parameters");
        }

        // Convert DTO to Entity
        Review review = new Review();
        review.setUuid(UUID.randomUUID().toString()); // Generate a unique UUID
        review.setServiceAgreement(serviceAgreement);
        review.setSession(session);
        if (!StringUtils.hasText(dto.getReviewerId()))
            throw new RuntimeException("Review has missing reviewer id");
        review.setReviewerId(dto.getReviewerId());
        if (!StringUtils.hasText(dto.getRevieweeId()))
            throw new RuntimeException("Review has missing reviewee id");
        review.setRevieweeId(dto.getRevieweeId());
        if (dto.getRating() < 0)
            throw new RuntimeException("Rating can't be negative");
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        if (dto.getSubmissionDate().compareTo(new Date()) >= 0)
            throw new RuntimeException("Invalid Submission Date");
        review.setSubmissionDate(dto.getSubmissionDate());
        review.setPublic(dto.isPublic());
        review.setRecommended(dto.isRecommended());

        // Save to database
        return "Review created successfully";
    }

    public ReviewDTO updateReviewField(Long id, Map<String, Object> updates) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review with ID " + id + " not found"));

        // Loop through the provided updates and set the fields dynamically
        updates.forEach((key, value) -> {
            switch (key) {
                case "rating" -> review.setRating(Integer.parseInt(value.toString()));
                case "submissionDate" -> review.setSubmissionDate(Date.from(Instant.parse(value.toString())));
                case "isPublic" -> review.setPublic(Boolean.parseBoolean(value.toString()));
                case "isRecommended" -> review.setRecommended(Boolean.parseBoolean(value.toString()));
                default -> throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        // Save updated entity
        Review updatedReview = reviewRepository.save(review);

        return convertToDTO(updatedReview);
    }
}
