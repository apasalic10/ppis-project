package ba.unsa.etf.nwt.controller;

import ba.unsa.etf.nwt.DTO.TeachingOfferingPostDTO;
import ba.unsa.etf.nwt.entity.TeachingOffering;
import ba.unsa.etf.nwt.service.TeachingOfferingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TeachingOfferingController.class)
public class TeachingOfferingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TeachingOfferingService teachingOfferingService;

    @Autowired
    private ObjectMapper objectMapper;

    private TeachingOfferingPostDTO dto;
    private TeachingOffering teachingOffering;

    @BeforeEach
    public void setup() {
        dto = new TeachingOfferingPostDTO();
        dto.setTeachingApproach("Interactive");
        dto.setMaxStudents(10);
        dto.setPrerequisites("Basic math");
        dto.setLearningOutcomes("Critical thinking");
        dto.setMaterials("Workbook");
        dto.setDurationMinutes(60);
        dto.setGroupSession(true);

        teachingOffering = new TeachingOffering();
        teachingOffering.setId(1L); // assuming there's an id field
    }

    @Test
    public void testCreateTeachingOffering_Success() throws Exception {
        when(teachingOfferingService.createTeachingOffering(dto)).thenReturn(teachingOffering);

        mockMvc.perform(post("/api/teaching-offerings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testCreateTeachingOffering_BadRequest() throws Exception {
        when(teachingOfferingService.createTeachingOffering(any(TeachingOfferingPostDTO.class)))
                .thenThrow(new RuntimeException("Invalid data"));

        mockMvc.perform(post("/api/teaching-offerings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }
}
