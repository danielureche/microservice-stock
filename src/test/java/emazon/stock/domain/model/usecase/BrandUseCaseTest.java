package emazon.stock.domain.model.usecase;

import emazon.stock.configuration.exception.BrandAlreadyExistsException;
import emazon.stock.configuration.exception.InvalidPageIndexException;
import emazon.stock.domain.model.Brand;
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
    void createBrandWhenBrandDoesNotExist() {
        // Arrange
        Brand brand = new Brand(1L, "TestBrand", "Description");
        when(brandPersistencePort.findByName(any(String.class))).thenReturn(Optional.empty());
        // Act
        brandUseCase.createBrand(brand);
        // Assert
        verify(brandPersistencePort, times(1)).createBrand(brand);
    }

    @Test
    void throwsBrandAlreadyExistsException() {
        // Arrange
        Brand brand = new Brand(1L, "TestBrand", "Description");
        when(brandPersistencePort.findByName(any(String.class))).thenReturn(Optional.of(brand));

        // Act & Assert
        assertThrows(BrandAlreadyExistsException.class, () -> brandUseCase.createBrand(brand));
        verify(brandPersistencePort, never()).createBrand(any(Brand.class));
    }

    @Test
    void testListBrands() {
        // Arrange
        PaginationUtil paginationUtil = new PaginationUtil(10, 1, "name", true);
        Pagination<Brand> expectedPagination = mock(Pagination.class);
        when(brandPersistencePort.listBrands(paginationUtil)).thenReturn(expectedPagination);

        // Act
        Pagination<Brand> result = brandUseCase.listBrands(paginationUtil);

        // Assert
        assertEquals(expectedPagination, result);
        verify(brandPersistencePort).listBrands(paginationUtil);
    }

    @Test
    void testListBrandsWithInvalidPageNumber() {
        // Arrange
        PaginationUtil paginationUtil = new PaginationUtil(10, -1, "name", true);

        // Act & Assert
        InvalidPageIndexException thrown = assertThrows(
                InvalidPageIndexException.class,
                () -> brandUseCase.listBrands(paginationUtil),
                "Expected listBrands() to throw, but it didn't"
        );

        assertEquals("Page index is out of range.", thrown.getMessage());
    }

    @Test
    void testListBrandsWithInvalidPageSize() {
        // Arrange
        PaginationUtil paginationUtil = new PaginationUtil(-1, 1, "name", true);

        // Act & Assert
        InvalidPageIndexException thrown = assertThrows(
                InvalidPageIndexException.class,
                () -> brandUseCase.listBrands(paginationUtil),
                "Expected listBrands() to throw, but it didn't"
        );

        assertEquals("Page index is out of range.", thrown.getMessage());
    }
}
