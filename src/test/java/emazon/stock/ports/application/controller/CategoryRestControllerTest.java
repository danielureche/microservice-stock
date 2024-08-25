package emazon.stock.ports.application.controller;

import static javax.management.Query.value;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import emazon.stock.domain.model.Category;
import emazon.stock.domain.ports.input.ICategoryServicePort;
import emazon.stock.ports.application.dto.request.CategoryRequest;
import emazon.stock.ports.application.mapper.request.ICategoryRequestMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CategoryRestController.class)
public class CategoryRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICategoryServicePort categoryServicePort;

    @MockBean
    private ICategoryRequestMapper categoryRequestMapper;

    @Autowired
    private ObjectMapper objectMapper;
    private byte[] jsonRequest;

    @Test
    public void testCreateCategorySuccess() throws Exception {
        // Given
        CategoryRequest categoryRequest = new CategoryRequest("New", "Description");
        Category category = new Category(1L, "New", "Description");

        // When
        when(categoryRequestMapper.toCategory(categoryRequest)).thenReturn(category);

        // Then
        mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryRequest)))
                .andExpect(status().isCreated());

        verify(categoryServicePort).createCategory(category);
    }
    @Test
    void shouldReturnBadRequestWhenNameIsBlank() throws Exception {
        // Arrange
        CategoryRequest categoryRequest = new CategoryRequest("", "A description");

        // Act & Assert
        mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenDescriptionIsBlank() throws Exception {
        // Arrange
        CategoryRequest categoryRequest = new CategoryRequest("Category Name", "");

        // Act & Assert
        mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryRequest)))
                .andExpect(status().isBadRequest());

    }

    @Test
    void shouldReturnBadRequestWhenNameExceedsMaxLength() throws Exception {
        // Arrange
        String longName = "A".repeat(51);
        CategoryRequest categoryRequest = new CategoryRequest(longName, "A description");

        // Act & Assert
        mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenDescriptionExceedsMaxLength() throws Exception {
        // Arrange
        String longDescription = "A".repeat(91);
        CategoryRequest categoryRequest = new CategoryRequest("Category Name", longDescription);

        // Act & Assert
        mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryRequest)))
                .andExpect(status().isBadRequest());
    }
}
