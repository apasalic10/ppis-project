package ba.unsa.etf.nwt.controller;

import ba.unsa.etf.nwt.DTO.SkillLevelDTO;
import ba.unsa.etf.nwt.service.SkillLevelService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SkillLevelController.class)
public class SkillLevelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SkillLevelService skillLevelService;

    @Autowired
    private ObjectMapper objectMapper;

    private SkillLevelDTO skillLevelDTO;

    @BeforeEach
    public void setup() {
        skillLevelDTO = new SkillLevelDTO();
        skillLevelDTO.setName("Beginner");
        skillLevelDTO.setDescription("Basic understanding of the topic");
        skillLevelDTO.setSortOrder(1);
    }

    @Test
    public void testCreateSkillLevel_Success() throws Exception {
        when(skillLevelService.createSkillLevel(any(SkillLevelDTO.class))).thenReturn(skillLevelDTO);

        mockMvc.perform(post("/api/skill-levels/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(skillLevelDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Beginner"));
    }

    @Test
    public void testCreateSkillLevel_BadRequest() throws Exception {
        when(skillLevelService.createSkillLevel(any(SkillLevelDTO.class)))
                .thenThrow(new RuntimeException("Validation error"));

        mockMvc.perform(post("/api/skill-levels/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(skillLevelDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetSkillLevelById_Success() throws Exception {
        when(skillLevelService.getSkillLevelById(1L)).thenReturn(skillLevelDTO);

        mockMvc.perform(get("/api/skill-levels/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Beginner"));
    }

    @Test
    public void testGetSkillLevelById_NotFound() throws Exception {
        when(skillLevelService.getSkillLevelById(999L)).thenThrow(new RuntimeException("Not found"));

        mockMvc.perform(get("/api/skill-levels/{id}", 999L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllSkillLevels_Success() throws Exception {
        when(skillLevelService.getAllSkillLevels()).thenReturn(List.of(skillLevelDTO));

        mockMvc.perform(get("/api/skill-levels/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Beginner"));
    }

    @Test
    public void testGetAllSkillLevels_ServerError() throws Exception {
        when(skillLevelService.getAllSkillLevels())
                .thenThrow(new RuntimeException("DB failure"));

        mockMvc.perform(get("/api/skill-levels/"))
                .andExpect(status().isInternalServerError());
    }
}