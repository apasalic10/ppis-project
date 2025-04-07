package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.DTO.ServiceAgreementDTO;
import ba.unsa.etf.nwt.DTO.ServiceAgreementPostDTO;
import ba.unsa.etf.nwt.entity.Review;
import ba.unsa.etf.nwt.entity.ServiceAgreement;
import ba.unsa.etf.nwt.entity.Session;
import ba.unsa.etf.nwt.repository.ServiceAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ServiceAgreementService {
    public final ServiceAgreementRepository serviceAgreementRepository;

    @Autowired
    public ServiceAgreementService(ServiceAgreementRepository serviceAgreementRepository) {
        this.serviceAgreementRepository = serviceAgreementRepository;
    }

    private ServiceAgreementDTO convertToDTO(ServiceAgreement sa) {

        List<String> sessionStatuses = (sa.getSessions() != null) ? sa.getSessions().stream()
                .map(Session::getStatus)
                .toList() : null;

        List<Integer> ratings = (sa.getReview() != null) ? sa.getReview().stream()
                .map(Review::getRating)
                .toList() : null;

        return new ServiceAgreementDTO(
                sa.getId(),
                sa.getTeacherId(),
                sa.getStudentId(),
                sa.getStartDate(),
                sa.getEndDate(),
                sa.getTerms(),
                (sa.getInvoice() != null) ? sa.getInvoice().getInvoiceNumber() : null,
                (sa.getLearningProgress() != null) ? sa.getLearningProgress().getCurrentLevel() : 0,
                (sa.getPayment() != null) ? sa.getPayment().getAmount() : 0,
                sessionStatuses,
                (ratings != null && !ratings.isEmpty()) ? ratings.stream().mapToInt(Integer::intValue).sum()/ ratings.size() : null
        );
    }

    public ServiceAgreementDTO getServiceAgreementDTOById(Long id) {
        ServiceAgreement sa = serviceAgreementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service Agreement not found with ID: " + id));

        return convertToDTO(sa);
    }

    public String createServiceAgreement(ServiceAgreementPostDTO dto) {
        if (dto.getTeacherId() == null || dto.getStudentId() == null || dto.getListingId() == null || dto.getStatus() == null || dto.getStartDate() == null || dto.getEndDate() == null || dto.getTerms() == null) {
            throw new RuntimeException("Service Agreement has missing parameters");
        }
        ServiceAgreement sa = new ServiceAgreement();
        sa.setUuid(UUID.randomUUID().toString()); // Generate a unique UUID
        sa.setTeacherId(dto.getTeacherId());
        sa.setStudentId(dto.getStudentId());
        sa.setListingId(dto.getListingId());
        if (!StringUtils.hasText(dto.getStatus()))
            throw new RuntimeException("Service Agreement has missing status");
        sa.setStatus(dto.getStatus());
        sa.setCreationDate(new Date());
        if (dto.getStartDate().compareTo(dto.getEndDate()) >= 0)
            throw new RuntimeException("End date cannot be greater than start date");
        if (dto.getStartDate().compareTo(new Date()) >= 0)
            throw new RuntimeException("Start date cannot be greater than creation date");
        sa.setStartDate(dto.getStartDate());
        sa.setEndDate(dto.getEndDate());
        if (!StringUtils.hasText(dto.getTerms()))
            throw new RuntimeException("Service Agreement has missing terms");
        sa.setTerms(dto.getTerms());
        sa.setAutoRenew(dto.isAutoRenew());

        // Save to database
        ServiceAgreement savedSA = serviceAgreementRepository.save(sa);

        // Convert back to DTO
        return "Created successfully";
    }

    public ServiceAgreementDTO updateServiceAgreementField(Long id, Map<String, Object> updates) {
        ServiceAgreement sa = serviceAgreementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service Agreement with ID " + id + " not found"));

        // Loop through the provided updates and set the fields dynamically
        updates.forEach((key, value) -> {
            switch (key) {
                case "status" -> sa.setStatus(value.toString());
                case "startDate" -> sa.setStartDate(Date.from(Instant.parse(value.toString())));
                case "endDate" -> sa.setEndDate(Date.from(Instant.parse(value.toString())));
                case "terms" -> sa.setTerms(value.toString());
                case "autoRenew" -> sa.setAutoRenew(Boolean.parseBoolean(value.toString()));
                default -> throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        // Save updated entity
        ServiceAgreement updatedSA = serviceAgreementRepository.save(sa);

        ServiceAgreementDTO updatedSADTO = convertToDTO(updatedSA);

        return updatedSADTO;
    }

}
