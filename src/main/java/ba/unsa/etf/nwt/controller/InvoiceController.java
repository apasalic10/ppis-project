package ba.unsa.etf.nwt.controller;

import ba.unsa.etf.nwt.DTO.InvoiceDTO;
import ba.unsa.etf.nwt.repository.InvoiceRepository;
import ba.unsa.etf.nwt.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceRepository invoiceRepository, InvoiceService invoiceService) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceService = invoiceService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getInvoiceById(@PathVariable long id) {
        try {
            InvoiceDTO dto = invoiceService.getInvoiceDTOById(id);
            return ResponseEntity.ok().body(dto);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Error message",e.getMessage()));
        }
    }
}
