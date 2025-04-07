package ba.unsa.etf.nwt;

import ba.unsa.etf.nwt.DTO.ServiceAgreementDTO;
import ba.unsa.etf.nwt.DTO.ServiceAgreementPostDTO;
import ba.unsa.etf.nwt.entity.ServiceAgreement;
import ba.unsa.etf.nwt.repository.ServiceAgreementRepository;
import ba.unsa.etf.nwt.service.ServiceAgreementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServiceAgreementServiceTest {

    @Mock
    private ServiceAgreementRepository repository;

    @InjectMocks
    private ServiceAgreementService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetServiceAgreementDTOById_found() {
        ServiceAgreement sa = new ServiceAgreement();
        sa.setId(1L);
        sa.setTeacherId("t1");
        sa.setStudentId("s1");
        sa.setStartDate(new Date(System.currentTimeMillis() - 100000));
        sa.setEndDate(new Date());
        sa.setTerms("Sample terms");

        when(repository.findById(1L)).thenReturn(Optional.of(sa));

        ServiceAgreementDTO dto = service.getServiceAgreementDTOById(1L);

        assertEquals("t1", dto.getTeacherId());
        assertEquals("s1", dto.getStudentId());
    }

    @Test
    public void testGetServiceAgreementDTOByIdNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            service.getServiceAgreementDTOById(99L);
        });

        assertTrue(thrown.getMessage().contains("Service Agreement not found with ID: " + 99L));
    }


    @Test
    void testCreateServiceAgreement_success() {
        ServiceAgreementPostDTO dto = new ServiceAgreementPostDTO("t1", "s1", "listing1", "ACTIVE", new Date(System.currentTimeMillis() - 100000), new Date(), "terms", true);
        when(repository.save(any(ServiceAgreement.class))).thenAnswer(i -> i.getArgument(0));

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            service.createServiceAgreement(dto);
        });
    }

    @Test
    void testCreateServiceAgreement_missingParameters() {
        ServiceAgreementPostDTO dto = new ServiceAgreementPostDTO();
        dto.setTeacherId("t1");
        when(repository.save(any(ServiceAgreement.class))).thenAnswer(i -> i.getArgument(0));

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            service.createServiceAgreement(dto);
        });

        assertTrue(thrown.getMessage().contains("Service Agreement has missing parameters"));
    }

    @Test
    void testCreateServiceAgreement_emptyStatus() {
        ServiceAgreementPostDTO dto = new ServiceAgreementPostDTO("t1", "s1", "listing1", "", new Date(System.currentTimeMillis() - 100000), new Date(), "terms", true);
        when(repository.save(any(ServiceAgreement.class))).thenAnswer(i -> i.getArgument(0));

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            service.createServiceAgreement(dto);
        });

        assertTrue(thrown.getMessage().contains("Service Agreement has missing status"));
    }

    @Test
    void testCreateServiceAgreement_startDateAndEndDate() {
        ServiceAgreementPostDTO dto = new ServiceAgreementPostDTO("t1", "s1", "listing1", "ACTIVE", new Date(), new Date(System.currentTimeMillis() - 100000), "terms", true);
        when(repository.save(any(ServiceAgreement.class))).thenAnswer(i -> i.getArgument(0));

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            service.createServiceAgreement(dto);
        });

        assertTrue(thrown.getMessage().contains("End date cannot be greater than start date"));
    }

    @Test
    void testCreateServiceAgreement_startDateAndCreationDate() {
        ServiceAgreementPostDTO dto = new ServiceAgreementPostDTO("t1", "s1", "listing1", "ACTIVE", new Date(System.currentTimeMillis() + 100000), new Date(System.currentTimeMillis() + 100001), "terms", true);
        when(repository.save(any(ServiceAgreement.class))).thenAnswer(i -> i.getArgument(0));

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            service.createServiceAgreement(dto);
        });

        assertTrue(thrown.getMessage().contains("Start date cannot be greater than creation date"));
    }

    @Test
    void testCreateServiceAgreement_emptyTerms() {
        ServiceAgreementPostDTO dto = new ServiceAgreementPostDTO("t1", "s1", "listing1", "ACTIVE", new Date(System.currentTimeMillis() - 100000), new Date(), "", true);
        when(repository.save(any(ServiceAgreement.class))).thenAnswer(i -> i.getArgument(0));

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            service.createServiceAgreement(dto);
        });

        assertTrue(thrown.getMessage().contains("Service Agreement has missing terms"));
    }

    @Test
    void testUpdateServiceAgreementField_success() {
        ServiceAgreement sa = new ServiceAgreement();
        sa.setId(1L);
        sa.setTerms("OLD");
        when(repository.findById(1L)).thenReturn(Optional.of(sa));
        when(repository.save(any(ServiceAgreement.class))).thenAnswer(i -> i.getArgument(0));

        Map<String, Object> updates = Map.of("terms", "NEW");
        ServiceAgreementDTO result = service.updateServiceAgreementField(1L, updates);

        assertEquals("NEW", result.getTerms());
    }

    @Test
    public void testUpdateServiceAgreementDTOById_notFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            service.updateServiceAgreementField(99L, Map.of("terms", "NEW"));
        });

        assertTrue(thrown.getMessage().contains("Service Agreement with ID " + 99L + " not found"));
    }

    @Test
    public void testUpdateServiceAgreementDTOById_invalidField() {
        ServiceAgreement sa = new ServiceAgreement();
        sa.setId(1L);
        sa.setTerms("OLD");
        when(repository.findById(1L)).thenReturn(Optional.of(sa));

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.updateServiceAgreementField(1L, Map.of("wrong", "NEW"));
        });

        assertTrue(thrown.getMessage().contains("Invalid field: wrong"));
    }
}