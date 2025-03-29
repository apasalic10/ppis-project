package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.DTO.InvoiceDTO;
import ba.unsa.etf.nwt.entity.Invoice;
import ba.unsa.etf.nwt.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {
    public final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
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
}
