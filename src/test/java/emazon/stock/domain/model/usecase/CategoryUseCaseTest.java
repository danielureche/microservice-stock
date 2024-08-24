package emazon.stock.domain.model.usecase;

import emazon.stock.configuration.exception.InvalidPageIndexException;
import emazon.stock.domain.model.Category;
import emazon.stock.domain.model.Pagination;
import emazon.stock.domain.ports.output.ICategoryPersistencePort;
import emazon.stock.configuration.exception.CategoryAlreadyExistsException;
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

public class CategoryUseCaseTest {
    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCategoryWhenCategoryDoesNotExist() {
        // Arrange
        Category category = new Category(1L, "TestCategory", "Description");
        when(categoryPersistencePort.findByName(any(String.class))).thenReturn(Optional.empty());
        // Act
        categoryUseCase.createCategory(category);
        // Assert
        verify(categoryPersistencePort, times(1)).createCategory(category);
    }

    @Test
    void throwsCategoryAlreadyExistsException() {
        // Arrange
        Category category = new Category(1L, "TestCategory", "Description");
        when(categoryPersistencePort.findByName(any(String.class))).thenReturn(Optional.of(category));

        // Act & Assert
        assertThrows(CategoryAlreadyExistsException.class, () -> categoryUseCase.createCategory(category));
        verify(categoryPersistencePort, never()).createCategory(any(Category.class));
    }

    @Test
    void testListCategories() {
        // Arrange
        PaginationUtil paginationUtil = new PaginationUtil(10, 1, "name", true);
        Pagination<Category> expectedPagination = mock(Pagination.class);
        when(categoryPersistencePort.listCategories(paginationUtil)).thenReturn(expectedPagination);

        // Act
        Pagination<Category> result = categoryUseCase.listCategories(paginationUtil);

        // Assert
        assertEquals(expectedPagination, result);
        verify(categoryPersistencePort).listCategories(paginationUtil);
    }

    @Test
    void testListCategoriesWithInvalidPageNumber() {
        // Arrange
        PaginationUtil paginationUtil = new PaginationUtil(10, -1, "name", true);

        // Act & Assert
        InvalidPageIndexException thrown = assertThrows(
                InvalidPageIndexException.class,
                () -> categoryUseCase.listCategories(paginationUtil),
                "Expected listCategories() to throw, but it didn't"
        );

        assertEquals("Page index is out of range ", thrown.getMessage());
    }

    @Test
    void testListCategoriesWithInvalidPageSize() {
        // Arrange
        PaginationUtil paginationUtil = new PaginationUtil(-1, 1, "name", true);

        // Act & Assert
        InvalidPageIndexException thrown = assertThrows(
                InvalidPageIndexException.class,
                () -> categoryUseCase.listCategories(paginationUtil),
                "Expected listCategories() to throw, but it didn't"
        );

        assertEquals("Page index is out of range ", thrown.getMessage());
    }
}
