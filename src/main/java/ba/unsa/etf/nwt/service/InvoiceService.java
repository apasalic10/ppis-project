package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.DTO.InvoiceDTO;
import ba.unsa.etf.nwt.DTO.InvoicePostDTO;
import ba.unsa.etf.nwt.entity.Invoice;
import ba.unsa.etf.nwt.entity.ServiceAgreement;
import ba.unsa.etf.nwt.repository.InvoiceRepository;
import ba.unsa.etf.nwt.repository.ServiceAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
public class InvoiceService {
    public final InvoiceRepository invoiceRepository;
    public final ServiceAgreementRepository serviceAgreementRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, ServiceAgreementRepository serviceAgreementRepository) {
        this.invoiceRepository = invoiceRepository;
        this.serviceAgreementRepository = serviceAgreementRepository;
    }

    private InvoiceDTO convertToDto(Invoice invoice) {
        return new InvoiceDTO(
                invoice.getId(),
                invoice.getInvoiceNumber(),
                invoice.getIssueDate(),
                invoice.getDueDate(),
                invoice.getTotalAmount(),
                invoice.getStatus(),
                (invoice.getServiceAgreement() != null) ? invoice.getServiceAgreement().getStatus(): null
        );
    }

    public Invoice getInvoiceById(long id) {
        return invoiceRepository.findById(id).orElse(null);
    }

    public InvoiceDTO getInvoiceDTOById(long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found with ID: " + id));

        return convertToDto(invoice);
    }

    public String createInvoice(InvoicePostDTO dto) {
        // Check if Service Agreement exists
        ServiceAgreement serviceAgreement = serviceAgreementRepository.findById(dto.getServiceAgreementId())
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        if(dto.getInvoiceNumber() == null || dto.getInvoiceNumber().isEmpty() || dto.getDueDate() == null || dto.getIssueDate() == null || dto.getStatus() == null)
            throw new RuntimeException("Invoice has null or empty information");
        // Convert DTO to Entity
        Invoice invoice = new Invoice();
        invoice.setUuid(UUID.randomUUID().toString()); // Generate a unique UUID
        invoice.setServiceAgreement(serviceAgreement);
        if (!StringUtils.hasText(dto.getInvoiceNumber()))
            throw new RuntimeException("Invoice has missing invoice number");
        invoice.setInvoiceNumber(dto.getInvoiceNumber());
        invoice.setIssueDate(dto.getIssueDate());
        invoice.setDueDate(dto.getDueDate());
        invoice.setTotalAmount(dto.getTotalAmount());
        if (!StringUtils.hasText(dto.getStatus()))
            throw new RuntimeException("Invoice has missing status");
        invoice.setStatus(dto.getStatus());

        // Save to database
        Invoice savedInvoice = invoiceRepository.save(invoice);

        return "Invoice created";
    }
}
