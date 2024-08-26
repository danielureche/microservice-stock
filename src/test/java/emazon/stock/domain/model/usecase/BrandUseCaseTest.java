package emazon.stock.domain.model.usecase;

import emazon.stock.configuration.exception.BrandAlreadyExistsException;
import emazon.stock.domain.model.Brand;
import emazon.stock.domain.model.Category;
import emazon.stock.domain.model.Pagination;
import emazon.stock.domain.ports.output.IBrandPersistencePort;
import emazon.stock.domain.util.PaginationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

class BrandUseCaseTest {
    @Mock
    private IBrandPersistencePort brandPersistencePort;

    @InjectMocks
    private BrandUseCase brandUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCategoryWhenBrandDoesNotExist() {
        // Arrange
        Brand brand = new Brand(1L, "TestBrand", "Description");
        when(brandPersistencePort.findByName(any(String.class))).thenReturn(Optional.empty());
        // Act
        brandUseCase.createBrand(brand);
        // Assert
        verify(brandPersistencePort, times(1)).createBrand(brand);
    }

    @Test
    void throwsCategoryAlreadyExistsException() {
        // Arrange
        Brand brand = new Brand(1L, "TestBrand", "Description");
        when(brandPersistencePort.findByName(any(String.class))).thenReturn(Optional.of(brand));

        // Act & Assert
        assertThrows(BrandAlreadyExistsException.class, () -> brandUseCase.createBrand(brand));
        verify(brandPersistencePort, never()).createBrand(any(Brand.class));
    }

}
