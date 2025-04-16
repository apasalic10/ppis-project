package ba.unsa.etf.nwt.controller;

import ba.unsa.etf.nwt.DTO.LearningRequestPostDTO;
import ba.unsa.etf.nwt.entity.LearningRequest;
import ba.unsa.etf.nwt.service.LearningRequestService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LearningRequestController.class)
public class LearningRequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LearningRequestService learningRequestService;

    @Autowired
    private ObjectMapper objectMapper;

    private LearningRequestPostDTO dto;

    @BeforeEach
    public void setup() {
        dto = new LearningRequestPostDTO();
        dto.setTitle("Math Help Needed");
        dto.setDescription("Need help with basic algebra.");
        dto.setStatus("ACTIVE");
        dto.setPrice(0f);
        dto.setPricingModel("FREE");
        dto.setFeatured(false);
        dto.setLearningGoal("Understand algebra basics");
        dto.setPreferredApproach("Visual");
        dto.setAvailabilityWindow("Weekdays after 5PM");
        dto.setUrgencyLevel(2);
        dto.setGroupLearningOk(true);
    }

    @Test
    public void testCreateLearningRequest_Success() throws Exception {
        LearningRequest learningRequest = new LearningRequest();
        when(learningRequestService.createLearningRequest(any(LearningRequestPostDTO.class)))
                .thenReturn(learningRequest);

        mockMvc.perform(post("/api/learning-requests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testCreateLearningRequest_BadRequest() throws Exception {
        when(learningRequestService.createLearningRequest(any(LearningRequestPostDTO.class)))
                .thenThrow(new RuntimeException("Invalid data"));

        mockMvc.perform(post("/api/learning-requests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }
}
