package emazon.stock.ports.persistence.adapter;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import emazon.stock.domain.model.Category;
import emazon.stock.domain.model.Pagination;
import emazon.stock.domain.util.PaginationUtil;
import emazon.stock.ports.persistence.entity.CategoryEntity;
import emazon.stock.ports.persistence.mapper.ICategoryEntityMapper;
import emazon.stock.ports.persistence.repository.ICategoryRepository;
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

class CategoryAdapterTest {
    @Mock
    private ICategoryRepository categoryRepository;

    @Mock
    private ICategoryEntityMapper categoryEntityMapper;

    @Mock
    private Page<CategoryEntity> categoryPage;

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

        when(categoryRepository.findByName(eq(categoryName))).thenReturn(Optional.of(categoryEntity));
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
        when(categoryRepository.findByName(eq(categoryName))).thenReturn(Optional.empty());

        // Act
        Optional<Category> foundCategory = categoryAdapter.findByName(categoryName);

        // Assert
        assertTrue(foundCategory.isEmpty());
    }

    @Test
    void listCategories_whenAscending() {
        // Arrange
        PaginationUtil paginationUtil = new PaginationUtil();
        paginationUtil.setPageNumber(0);
        paginationUtil.setPageSize(10);
        paginationUtil.setAscending(true);
        paginationUtil.setNameFilter("name");

        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        PageRequest pageRequest = PageRequest.of(0, 10, sort);

        List<CategoryEntity> categoryEntities = Collections.emptyList();
        List<Category> categories = Collections.emptyList();

        when(categoryRepository.findAll(pageRequest)).thenReturn(categoryPage);
        when(categoryPage.getContent()).thenReturn(categoryEntities);
        when(categoryPage.getTotalPages()).thenReturn(1);
        when(categoryPage.getTotalElements()).thenReturn(10L);
        when(categoryEntityMapper.toCategoryList(categoryEntities)).thenReturn(categories);

        // Act
        Pagination<Category> result = categoryAdapter.listCategories(paginationUtil);

        // Assert
        assertEquals(1, result.getTotalPages());
        assertEquals(10L, result.getTotalElements());
        assertEquals(categories, result.getContent());

        verify(categoryRepository).findAll(pageRequest);
        verify(categoryPage).getContent();
        verify(categoryPage).getTotalPages();
        verify(categoryPage).getTotalElements();
        verify(categoryEntityMapper).toCategoryList(categoryEntities);
    }

    @Test
    void listCategories_whenDescending() {
        // Arrange
        PaginationUtil paginationUtil = new PaginationUtil();
        paginationUtil.setPageNumber(0);
        paginationUtil.setPageSize(10);
        paginationUtil.setAscending(false);
        paginationUtil.setNameFilter("name");

        Sort sort = Sort.by(Sort.Direction.DESC, "name");
        PageRequest pageRequest = PageRequest.of(0, 10, sort);

        List<CategoryEntity> categoryEntities = Collections.emptyList();
        List<Category> categories = Collections.emptyList();

        when(categoryRepository.findAll(pageRequest)).thenReturn(categoryPage);
        when(categoryPage.getContent()).thenReturn(categoryEntities);
        when(categoryPage.getTotalPages()).thenReturn(1);
        when(categoryPage.getTotalElements()).thenReturn(10L);
        when(categoryEntityMapper.toCategoryList(categoryEntities)).thenReturn(categories);

        // Act
        Pagination<Category> result = categoryAdapter.listCategories(paginationUtil);

        // Assert
        assertEquals(1, result.getTotalPages());
        assertEquals(10L, result.getTotalElements());
        assertEquals(categories, result.getContent());

        verify(categoryRepository).findAll(pageRequest);
        verify(categoryPage).getContent();
        verify(categoryPage).getTotalPages();
        verify(categoryPage).getTotalElements();
        verify(categoryEntityMapper).toCategoryList(categoryEntities);
    }

    @Test
    void testExistsCategoryById() {
        // Arrange
        Long categoryId = 1L;
        when(categoryRepository.existsById(categoryId)).thenReturn(true);

        // Act
        boolean result = categoryAdapter.existsCategory(categoryId);

        // Assert
        assertTrue(result);
        verify(categoryRepository).existsById(categoryId);
    }
}
