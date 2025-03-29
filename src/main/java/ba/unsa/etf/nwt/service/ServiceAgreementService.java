package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.DTO.ServiceAgreementDTO;
import ba.unsa.etf.nwt.entity.Review;
import ba.unsa.etf.nwt.entity.ServiceAgreement;
import ba.unsa.etf.nwt.entity.Session;
import ba.unsa.etf.nwt.repository.ServiceAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
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
                (sa.getLearningProgress() != null) ? sa.getLearningProgress().getCurrentLevel() : null,
                (sa.getPayment() != null) ? sa.getPayment().getAmount() : null,
                sessionStatuses,
                (ratings != null && !ratings.isEmpty()) ? ratings.stream().mapToInt(Integer::intValue).sum()/ ratings.size() : null
        );
    }

    public ServiceAgreementDTO getServiceAgreementDTOById(Long id) {
        ServiceAgreement sa = serviceAgreementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service Agreement not found with ID: " + id));

        return convertToDTO(sa);
    }
}
