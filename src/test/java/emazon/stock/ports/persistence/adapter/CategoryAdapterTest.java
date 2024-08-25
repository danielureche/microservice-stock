package emazon.stock.ports.persistence.adapter;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import emazon.stock.domain.model.Category;
import emazon.stock.ports.persistence.entity.CategoryEntity;
import emazon.stock.ports.persistence.mapper.ICategoryEntityMapper;
import emazon.stock.ports.persistence.repository.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

class CategoryAdapterTest {
    @Mock
    private ICategoryRepository categoryRepository;

    @Mock
    private ICategoryEntityMapper categoryEntityMapper;

    @InjectMocks
    private CategoryAdapter categoryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateCategory() {
        // Arrange
        Category category = new Category(null, "Category Name", "A description");
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName("Category Name");
        categoryEntity.setDescription("A description");

        when(categoryEntityMapper.toEntity(any(Category.class))).thenReturn(categoryEntity);

        // Act
        categoryAdapter.createCategory(category);

        // Assert
        verify(categoryRepository).save(categoryEntity);
    }

    @Test
    void shouldReturnCategoryWhenFindByNameExists() {
        // Arrange
        String categoryName = "Existing Category";
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryName);
        categoryEntity.setDescription("A description");

        when(categoryRepository.findByName(eq(categoryName))).thenReturn(categoryEntity);
        when(categoryEntityMapper.toModel(categoryEntity)).thenReturn(new Category(null, categoryName, "A description"));

        // Act
        Optional<Category> foundCategory = categoryAdapter.findByName(categoryName);

        // Assert
        assertTrue(foundCategory.isPresent());
        assertEquals(categoryName, foundCategory.get().getName());
    }

    @Test
    void shouldReturnEmptyWhenFindByNameDoesNotExist() {
        // Arrange
        String categoryName = "Non-Existing Category";
        when(categoryRepository.findByName(eq(categoryName))).thenReturn(null);

        // Act
        Optional<Category> foundCategory = categoryAdapter.findByName(categoryName);

        // Assert
        assertTrue(foundCategory.isEmpty());
    }

}
