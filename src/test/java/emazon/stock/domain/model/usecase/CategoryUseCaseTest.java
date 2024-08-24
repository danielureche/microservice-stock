package emazon.stock.domain.model.usecase;

import emazon.stock.domain.model.Category;
import emazon.stock.domain.ports.output.ICategoryPersistencePort;
import emazon.stock.configuration.exception.CategoryAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;


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
}
