package emazon.stock.ports.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import emazon.stock.domain.model.Brand;
import emazon.stock.domain.ports.input.IBrandServicePort;
import emazon.stock.ports.application.dto.request.BrandRequest;
import emazon.stock.ports.application.mapper.request.IBrandRequestMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BrandRestController.class)
public class BrandRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IBrandServicePort brandServicePort;

    @MockBean
    private IBrandRequestMapper brandRequestMapper;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void testCreateBrandSuccess() throws Exception {
        // Given
        BrandRequest brandRequest = new BrandRequest("New", "Description");
        Brand brand = new Brand(1L, "New", "Description");

        // When
        when(brandRequestMapper.toBrand(brandRequest)).thenReturn(brand);

        // Then
        mockMvc.perform(post("/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(brandRequest)))
                .andExpect(status().isCreated());

        verify(brandServicePort).createBrand(brand);
    }

    @Test
    void shouldReturnBadRequestWhenNameIsBlank() throws Exception {
        // Arrange
        BrandRequest brandRequest = new BrandRequest("", "A description");

        // Act & Assert
        mockMvc.perform(post("/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(brandRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenDescriptionIsBlank() throws Exception {
        // Arrange
        BrandRequest brandRequest = new BrandRequest("Category Name", "");

        // Act & Assert
        mockMvc.perform(post("/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(brandRequest)))
                .andExpect(status().isBadRequest());

    }

    @Test
    void shouldReturnBadRequestWhenNameExceedsMaxLength() throws Exception {
        // Arrange
        String longName = "A".repeat(51);
        BrandRequest brandRequest = new BrandRequest(longName, "A description");

        // Act & Assert
        mockMvc.perform(post("/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(brandRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenDescriptionExceedsMaxLength() throws Exception {
        // Arrange
        String longDescription = "A".repeat(121);
        BrandRequest brandRequest = new BrandRequest("Category Name", longDescription);

        // Act & Assert
        mockMvc.perform(post("/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(brandRequest)))
                .andExpect(status().isBadRequest());
    }
}
