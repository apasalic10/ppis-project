package ba.unsa.etf.nwt.controller;

import ba.unsa.etf.nwt.DTO.PaymentDTO;
import ba.unsa.etf.nwt.repository.PaymentRepository;
import ba.unsa.etf.nwt.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentServiceController {
    private final PaymentRepository paymentRepository;
    private final PaymentService paymentService;

    @Autowired
    public PaymentServiceController(PaymentRepository paymentRepository, PaymentService paymentService) {
        this.paymentRepository = paymentRepository;
        this.paymentService = paymentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPaymentById(@PathVariable long id) {
        try{
            PaymentDTO dto = paymentService.getPaymentDTOById(id);
            return ResponseEntity.ok().body(dto);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Error message", e.getMessage()));
        }
    }
}
