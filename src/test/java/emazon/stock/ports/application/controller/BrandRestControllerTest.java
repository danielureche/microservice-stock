package emazon.stock.ports.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import emazon.stock.domain.model.Brand;
import emazon.stock.domain.model.Pagination;
import emazon.stock.domain.ports.input.IBrandServicePort;
import emazon.stock.domain.util.PaginationUtil;
import emazon.stock.ports.application.dto.request.BrandRequest;
import emazon.stock.ports.application.dto.response.BrandResponse;
import emazon.stock.ports.application.mapper.request.IBrandRequestMapper;
import emazon.stock.ports.application.mapper.response.IBrandResponseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BrandRestController.class)
class BrandRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IBrandServicePort brandServicePort;

    @MockBean
    private IBrandRequestMapper brandRequestMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IBrandResponseMapper brandResponseMapper;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBrandSuccess() throws Exception {
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

    @Test
    void testListBrands() throws Exception {
        // Arrange
        List<Brand> brandList = List.of(
                new Brand(1L, "Brand 1", "Description 1"),
                new Brand(2L, "Brand 2", "Description 2")
        );
        Pagination<Brand> pagination = new Pagination<>(true, 1, 2, 20L, brandList);
        doReturn(pagination).when(brandServicePort).listBrands(any(PaginationUtil.class));

        List<BrandResponse> brandResponseList = List.of(
                new BrandResponse(1L, "Brand 1", "Description 1"),
                new BrandResponse(2L, "Brand 2", "Description 2")
        );
        doReturn(brandResponseList).when(brandResponseMapper).toBrandResponses(brandList);

        // Act
        ResultActions resultActions = this.mockMvc.perform(get("/brands")
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
                .andExpect(jsonPath("$.content[0].name", is("Brand 1")))
                .andExpect(jsonPath("$.content[1].name", is("Brand 2")));
    }
}
