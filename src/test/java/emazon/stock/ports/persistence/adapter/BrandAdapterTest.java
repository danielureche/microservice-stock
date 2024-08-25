package emazon.stock.ports.persistence.adapter;

import emazon.stock.domain.model.Brand;
import emazon.stock.ports.persistence.entity.BrandEntity;
import emazon.stock.ports.persistence.mapper.IBrandEntityMapper;
import emazon.stock.ports.persistence.repository.IBrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
}
