package emazon.stock.ports.persistence.adapter;

import emazon.stock.domain.model.Brand;
import emazon.stock.domain.model.Pagination;
import emazon.stock.domain.util.PaginationUtil;
import emazon.stock.ports.persistence.entity.BrandEntity;
import emazon.stock.ports.persistence.mapper.IBrandEntityMapper;
import emazon.stock.ports.persistence.repository.IBrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BrandAdapterTest {
    @Mock
    private IBrandRepository brandRepository;

    @Mock
    private IBrandEntityMapper brandEntityMapper;

    @Mock
    private Page<BrandEntity> brandPage;

    @InjectMocks
    private BrandAdapter brandAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateCategory() {
        // Arrange
        Brand brand = new Brand(null, "Category Name", "A description");
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName("Brand Name");
        brandEntity.setDescription("A description");

        when(brandEntityMapper.toEntity(any(Brand.class))).thenReturn(brandEntity);

        // Act
        brandAdapter.createBrand(brand);

        // Assert
        verify(brandRepository).save(brandEntity);
    }

    @Test
    void shouldReturnCategoryWhenFindByNameExists() {
        // Arrange
        String brandName = "Existing Category";
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName(brandName);
        brandEntity.setDescription("A description");

        when(brandRepository.findByName(eq(brandName))).thenReturn(brandEntity);
        when(brandEntityMapper.toModel(brandEntity)).thenReturn(new Brand(null, brandName, "A description"));

        // Act
        Optional<Brand> foundBrand = brandAdapter.findByName(brandName);

        // Assert
        assertTrue(foundBrand.isPresent());
        assertEquals(brandName, foundBrand.get().getName());
    }

    @Test
    void shouldReturnEmptyWhenFindByNameDoesNotExist() {
        // Arrange
        String brandName = "Non-Existing Category";
        when(brandRepository.findByName(eq(brandName))).thenReturn(null);

        // Act
        Optional<Brand> foundBrand = brandAdapter.findByName(brandName);

        // Assert
        assertTrue(foundBrand.isEmpty());
    }

    @Test
    void listBrands_whenAscending() {
        // Arrange
        PaginationUtil paginationUtil = new PaginationUtil();
        paginationUtil.setPageNumber(0);
        paginationUtil.setPageSize(10);
        paginationUtil.setAscending(true);
        paginationUtil.setNameFilter("name");

        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        PageRequest pageRequest = PageRequest.of(0, 10, sort);

        List<BrandEntity> brandEntities = Collections.emptyList();
        List<Brand> brands = Collections.emptyList();

        when(brandRepository.findAll(pageRequest)).thenReturn(brandPage);
        when(brandPage.getContent()).thenReturn(brandEntities);
        when(brandPage.getTotalPages()).thenReturn(1);
        when(brandPage.getTotalElements()).thenReturn(10L);
        when(brandEntityMapper.toBrandList(brandEntities)).thenReturn(brands);

        // Act
        Pagination<Brand> result = brandAdapter.listBrands(paginationUtil);

        // Assert
        assertEquals(1, result.getTotalPages());
        assertEquals(10L, result.getTotalElements());
        assertEquals(brands, result.getContent());

        verify(brandRepository).findAll(pageRequest);
        verify(brandPage).getContent();
        verify(brandPage).getTotalPages();
        verify(brandPage).getTotalElements();
        verify(brandEntityMapper).toBrandList(brandEntities);
    }

    @Test
    void listBrands_whenDescending() {
        // Arrange
        PaginationUtil paginationUtil = new PaginationUtil();
        paginationUtil.setPageNumber(0);
        paginationUtil.setPageSize(10);
        paginationUtil.setAscending(false);
        paginationUtil.setNameFilter("name");

        Sort sort = Sort.by(Sort.Direction.DESC, "name");
        PageRequest pageRequest = PageRequest.of(0, 10, sort);

        List<BrandEntity> brandEntities = Collections.emptyList();
        List<Brand> brands = Collections.emptyList();

        when(brandRepository.findAll(pageRequest)).thenReturn(brandPage);
        when(brandPage.getContent()).thenReturn(brandEntities);
        when(brandPage.getTotalPages()).thenReturn(1);
        when(brandPage.getTotalElements()).thenReturn(10L);
        when(brandEntityMapper.toBrandList(brandEntities)).thenReturn(brands);

        // Act
        Pagination<Brand> result = brandAdapter.listBrands(paginationUtil);

        // Assert
        assertEquals(1, result.getTotalPages());
        assertEquals(10L, result.getTotalElements());
        assertEquals(brands, result.getContent());

        verify(brandRepository).findAll(pageRequest);
        verify(brandPage).getContent();
        verify(brandPage).getTotalPages();
        verify(brandPage).getTotalElements();
        verify(brandEntityMapper).toBrandList(brandEntities);
    }
}
