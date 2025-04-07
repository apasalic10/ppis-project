package ba.unsa.etf.nwt;

import ba.unsa.etf.nwt.DTO.ServiceAgreementDTO;
import ba.unsa.etf.nwt.DTO.ServiceAgreementPostDTO;
import ba.unsa.etf.nwt.controller.ServiceAgreementController;
import ba.unsa.etf.nwt.repository.ServiceAgreementRepository;
import ba.unsa.etf.nwt.service.ServiceAgreementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ServiceAgreementController.class)
public class ServiceAgreementControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ServiceAgreementRepository serviceAgreementRepository;

    @MockitoBean
    private ServiceAgreementService serviceAgreementService;

    @Autowired
    private ObjectMapper objectMapper;

    private ServiceAgreementDTO serviceAgreementDTO;
    private ServiceAgreementPostDTO serviceAgreementPostDTO;

    @BeforeEach
    public void setup() {
        serviceAgreementDTO = new ServiceAgreementDTO(
                1L,
                "teacher123",
                "student456",
                new Date(),
                new Date(System.currentTimeMillis() + 86400000),
                "Test terms",
                "INV-001",
                2,
                100.0f,
                Arrays.asList("SCHEDULED", "COMPLETED"),
                4
        );

        serviceAgreementPostDTO = new ServiceAgreementPostDTO(
                "teacher123",
                "student456",
                "listing789",
                "ACTIVE",
                new Date(System.currentTimeMillis() - 86400000),
                new Date(System.currentTimeMillis() + 86400000),
                "Test terms and conditions",
                true
        );
    }

    @Test
    public void testGetServiceAgreementById_Success() throws Exception {
        Long id = 1L;
        when(serviceAgreementService.getServiceAgreementDTOById(id)).thenReturn(serviceAgreementDTO);

        mockMvc.perform(get("/api/serviceAgreement/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.serviceAgreementId").value(serviceAgreementDTO.getServiceAgreementId()))
                .andExpect(jsonPath("$.teacherId").value(serviceAgreementDTO.getTeacherId()))
                .andExpect(jsonPath("$.studentId").value(serviceAgreementDTO.getStudentId()))
                .andExpect(jsonPath("$.terms").value(serviceAgreementDTO.getTerms()))
                .andExpect(jsonPath("$.invoiceNumber").value(serviceAgreementDTO.getInvoiceNumber()))
                .andExpect(jsonPath("$.currentLevel").value(serviceAgreementDTO.getCurrentLevel()))
                .andExpect(jsonPath("$.amount").value(serviceAgreementDTO.getAmount()))
                .andExpect(jsonPath("$.ratings").value(serviceAgreementDTO.getRatings()));
    }

    @Test
    public void testGetServiceAgreementById_NotFound() throws Exception {
        Long id = 99L;
        when(serviceAgreementService.getServiceAgreementDTOById(id))
                .thenThrow(new RuntimeException("Service Agreement not found with ID: " + id));

        mockMvc.perform(get("/api/serviceAgreement/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$['Error message']").value("Service Agreement not found with ID: " + id));
    }

    @Test
    public void testCreateServiceAgreement_Success() throws Exception {
        String successMessage = "Created successfully";
        when(serviceAgreementService.createServiceAgreement(any(ServiceAgreementPostDTO.class)))
                .thenReturn(successMessage);

        mockMvc.perform(post("/api/serviceAgreement")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(serviceAgreementPostDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().string(successMessage));
    }

    @Test
    public void testCreateServiceAgreement_MissingFields() throws Exception {
        ServiceAgreementPostDTO invalidDTO = new ServiceAgreementPostDTO();
        invalidDTO.setTeacherId("teacher123");

        when(serviceAgreementService.createServiceAgreement(any(ServiceAgreementPostDTO.class)))
                .thenThrow(new RuntimeException("Service Agreement has missing parameters"));

        mockMvc.perform(post("/api/serviceAgreement")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDTO)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$['Error message']").value("Service Agreement has missing parameters"));
    }

    @Test
    public void testCreateServiceAgreement_InvalidDates() throws Exception {
        ServiceAgreementPostDTO invalidDatesDTO = new ServiceAgreementPostDTO(
                "teacher123",
                "student456",
                "listing789",
                "ACTIVE",
                new Date(System.currentTimeMillis() + 86400000),
                new Date(System.currentTimeMillis()),
                "Test terms",
                true
        );

        when(serviceAgreementService.createServiceAgreement(any(ServiceAgreementPostDTO.class)))
                .thenThrow(new RuntimeException("Start date cannot be greater than creation date"));

        mockMvc.perform(post("/api/serviceAgreement")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDatesDTO)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$['Error message']").value("Start date cannot be greater than creation date"));
    }

    @Test
    public void testUpdateServiceAgreementField_Success() throws Exception {
        Long id = 1L;
        Map<String, Object> updates = new HashMap<>();
        updates.put("status", "RENEWED");
        updates.put("terms", "Updated terms");

        when(serviceAgreementService.updateServiceAgreementField(eq(id), any(Map.class)))
                .thenReturn(serviceAgreementDTO);

        mockMvc.perform(patch("/api/serviceAgreement/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updates)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.serviceAgreementId").value(serviceAgreementDTO.getServiceAgreementId()))
                .andExpect(jsonPath("$.teacherId").value(serviceAgreementDTO.getTeacherId()));
    }

    @Test
    public void testUpdateServiceAgreementField_NotFound() throws Exception {
        Long id = 99L;
        Map<String, Object> updates = new HashMap<>();
        updates.put("status", "RENEWED");

        when(serviceAgreementService.updateServiceAgreementField(eq(id), any(Map.class)))
                .thenThrow(new RuntimeException("Service Agreement with ID " + id + " not found"));

        mockMvc.perform(patch("/api/serviceAgreement/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updates)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$['Error message']").value("Service Agreement with ID " + id + " not found"));
    }

    @Test
    public void testUpdateServiceAgreementField_InvalidField() throws Exception {

        Long id = 1L;
        Map<String, Object> updates = new HashMap<>();
        updates.put("invalidField", "someValue");

        when(serviceAgreementService.updateServiceAgreementField(eq(id), any(Map.class)))
                .thenThrow(new IllegalArgumentException("Invalid field: invalidField"));


        mockMvc.perform(patch("/api/serviceAgreement/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updates)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$['Error message']").value("Invalid field: invalidField"));
    }
}