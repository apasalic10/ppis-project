package ba.unsa.etf.nwt.controller;

import ba.unsa.etf.nwt.DTO.ListingDTO;
import ba.unsa.etf.nwt.controller.ListingController;
import ba.unsa.etf.nwt.service.ListingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.data.domain.*;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ListingController.class)
public class ListingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ListingService listingService;

    @Autowired
    private ObjectMapper objectMapper;

    private ListingDTO listingDTO;

    @BeforeEach
    public void setup() {
        listingDTO = new ListingDTO();
        listingDTO.setTitle("Test listing");
        listingDTO.setStatus("ACTIVE");
        listingDTO.setPrice(100.0f);
    }

    @Test
    public void testGetListingById_Success() throws Exception {
        when(listingService.getListingDTOById(1L)).thenReturn(listingDTO);

        mockMvc.perform(get("/api/listings/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(listingDTO.getTitle()))
                .andExpect(jsonPath("$.status").value(listingDTO.getStatus()))
                .andExpect(jsonPath("$.price").value(listingDTO.getPrice()));
    }

    @Test
    public void testGetListingById_NotFound() throws Exception {
        when(listingService.getListingDTOById(99L)).thenThrow(new RuntimeException("Listing not found"));

        mockMvc.perform(get("/api/listings/{id}", 99L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllListings_Success() throws Exception {
        Page<ListingDTO> listingPage = new PageImpl<>(List.of(listingDTO));
        when(listingService.getAllListings(any(Pageable.class))).thenReturn(listingPage);

        mockMvc.perform(get("/api/listings/")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title").value(listingDTO.getTitle()));
    }

    @Test
    public void testGetAllListings_ServerError() throws Exception {
        when(listingService.getAllListings(any(Pageable.class))).thenThrow(new RuntimeException("Server error"));

        mockMvc.perform(get("/api/listings/"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testFilterListings_Success() throws Exception {
        List<ListingDTO> listings = List.of(listingDTO);
        when(listingService.filterListings("ACTIVE", 50f, 150f, "Test")).thenReturn(listings);

        mockMvc.perform(get("/api/listings/filter")
                        .param("status", "ACTIVE")
                        .param("minPrice", "50")
                        .param("maxPrice", "150")
                        .param("title", "Test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(listingDTO.getTitle()));
    }

    @Test
    public void testFilterListings_ServerError() throws Exception {
        when(listingService.filterListings(any(), any(), any(), any()))
                .thenThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(get("/api/listings/filter"))
                .andExpect(status().isInternalServerError());
    }
}
