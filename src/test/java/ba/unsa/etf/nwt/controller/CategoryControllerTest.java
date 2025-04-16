package ba.unsa.etf.nwt.controller;

import ba.unsa.etf.nwt.DTO.CategoryPostDTO;
import ba.unsa.etf.nwt.entity.Category;
import ba.unsa.etf.nwt.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    private CategoryPostDTO categoryPostDTO;
    private Category category;

    @BeforeEach
    public void setup() {
        categoryPostDTO = new CategoryPostDTO();
        categoryPostDTO.setName("Math");
        categoryPostDTO.setDescription("Math related topics");
        categoryPostDTO.setIconUrl("https://example.com/icon.png");
        categoryPostDTO.setSortOrder(1);

        category = new Category();
        category.setId(1L);
        category.setName("Math");
        category.setDescription("Math related topics");
        category.setIconUrl("https://example.com/icon.png");
        category.setSortOrder(1);
    }

    @Test
    public void testCreateCategory_Success() throws Exception {
        when(categoryService.createCategory(any(CategoryPostDTO.class))).thenReturn(category);

        mockMvc.perform(post("/api/categories/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryPostDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testCreateCategory_BadRequest() throws Exception {
        when(categoryService.createCategory(any(CategoryPostDTO.class)))
                .thenThrow(new RuntimeException("Validation failed"));

        mockMvc.perform(post("/api/categories/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryPostDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetCategoryById_Success() throws Exception {
        when(categoryService.getCategoryById(1L)).thenReturn(category);

        mockMvc.perform(get("/api/categories/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(category.getName()));
    }

    @Test
    public void testGetCategoryById_NotFound() throws Exception {
        when(categoryService.getCategoryById(999L))
                .thenThrow(new RuntimeException("Not found"));

        mockMvc.perform(get("/api/categories/{id}", 999L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllCategories_Success() throws Exception {
        when(categoryService.getAllCategories()).thenReturn(List.of(category));

        mockMvc.perform(get("/api/categories/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(category.getName()));
    }

    @Test
    public void testGetAllCategories_ServerError() throws Exception {
        when(categoryService.getAllCategories())
                .thenThrow(new RuntimeException("Database error"));

        mockMvc.perform(get("/api/categories/"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testUpdateCategory_Success() throws Exception {
        Map<String, Object> updates = Map.of("name", "Updated Name");
        category.setName("Updated Name");

        when(categoryService.updateCategoryField(eq(1L), any(Map.class)))
                .thenReturn(category);

        mockMvc.perform(patch("/api/categories/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updates)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Name"));
    }

    @Test
    public void testUpdateCategory_BadRequest() throws Exception {
        Map<String, Object> updates = Map.of("invalid", "???");

        when(categoryService.updateCategoryField(eq(1L), any(Map.class)))
                .thenThrow(new RuntimeException("Invalid field"));

        mockMvc.perform(patch("/api/categories/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updates)))
                .andExpect(status().isBadRequest());
    }
}