package ba.unsa.etf.nwt.controller;

import ba.unsa.etf.nwt.DTO.TagPostDTO;
import ba.unsa.etf.nwt.entity.Tag;
import ba.unsa.etf.nwt.service.TagService;
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

@WebMvcTest(TagController.class)
public class TagControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TagService tagService;

    @Autowired
    private ObjectMapper objectMapper;

    private TagPostDTO tagPostDTO;
    private Tag tag;

    @BeforeEach
    public void setup() {
        tagPostDTO = new TagPostDTO("Java", 100);
        tag = new Tag();
        tag.setId(1L);
        tag.setName("Java");
        tag.setUsageCount(100);
    }

    @Test
    public void testCreateTag_Success() throws Exception {
        when(tagService.createTag(any(TagPostDTO.class))).thenReturn(tag);

        mockMvc.perform(post("/api/tags/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tagPostDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Java"))
                .andExpect(jsonPath("$.usageCount").value(100));
    }

    @Test
    public void testCreateTag_BadRequest() throws Exception {
        when(tagService.createTag(any(TagPostDTO.class)))
                .thenThrow(new RuntimeException("Validation error"));

        mockMvc.perform(post("/api/tags/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tagPostDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetTagById_Success() throws Exception {
        when(tagService.getTagById(1L)).thenReturn(tag);

        mockMvc.perform(get("/api/tags/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Java"))
                .andExpect(jsonPath("$.usageCount").value(100));
    }

    @Test
    public void testGetTagById_NotFound() throws Exception {
        when(tagService.getTagById(999L)).thenThrow(new RuntimeException("Not found"));

        mockMvc.perform(get("/api/tags/{id}", 999L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllTags_Success() throws Exception {
        when(tagService.getAllTags()).thenReturn(List.of(tag));

        mockMvc.perform(get("/api/tags/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Java"))
                .andExpect(jsonPath("$[0].usageCount").value(100));
    }

    @Test
    public void testGetAllTags_ServerError() throws Exception {
        when(tagService.getAllTags()).thenThrow(new RuntimeException("DB failure"));

        mockMvc.perform(get("/api/tags/"))
                .andExpect(status().isInternalServerError());
    }
}