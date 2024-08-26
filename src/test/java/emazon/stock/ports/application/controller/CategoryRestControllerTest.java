package emazon.stock.ports.application.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

import com.fasterxml.jackson.databind.ObjectMapper;
import emazon.stock.domain.model.Category;
import emazon.stock.domain.model.Pagination;
import emazon.stock.domain.ports.input.ICategoryServicePort;
import emazon.stock.domain.util.PaginationUtil;
import emazon.stock.ports.application.dto.request.CategoryRequest;
import emazon.stock.ports.application.dto.response.CategoryResponse;
import emazon.stock.ports.application.mapper.request.ICategoryRequestMapper;
import emazon.stock.ports.application.mapper.response.ICategoryResponseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@WebMvcTest(CategoryRestController.class)
class CategoryRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICategoryServicePort categoryServicePort;

    @MockBean
    private ICategoryRequestMapper categoryRequestMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ICategoryResponseMapper categoryResponseMapper;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCategorySuccess() throws Exception {
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

    @Test
    void testListCategories() throws Exception {
        // Arrange
        List<Category> categoryList = List.of(
                new Category(1L, "Category 1", "Description 1"),
                new Category(2L, "Category 2", "Description 2")
        );
        Pagination<Category> pagination = new Pagination<>(true, 1, 2, 20L, categoryList);
        doReturn(pagination).when(categoryServicePort).listCategories(any(PaginationUtil.class));

        List<CategoryResponse> categoryResponseList = List.of(
                new CategoryResponse(1L, "Category 1", "Description 1"),
                new CategoryResponse(2L, "Category 2", "Description 2")
        );
        doReturn(categoryResponseList).when(categoryResponseMapper).toCategoryResponses(categoryList);

        // Act
        ResultActions resultActions = this.mockMvc.perform(get("/categories")
                .param("size", String.valueOf(1))
                .param("page", String.valueOf(1))
                .param("nameFilter", "nameFilter1")
                .param("isAscending", String.valueOf(true))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        // Assert
        resultActions.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalPages", is(2)))
                .andExpect(jsonPath("$.ascending", is(true)))
                .andExpect(jsonPath("$.currentPage", is(1)))
                .andExpect(jsonPath("$.totalElements", is(20)))  // Comparar con Integer en lugar de Long
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].name", is("Category 1")))
                .andExpect(jsonPath("$.content[1].name", is("Category 2")));
    }
}
