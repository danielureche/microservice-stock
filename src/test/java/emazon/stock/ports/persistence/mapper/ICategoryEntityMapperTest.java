package emazon.stock.ports.persistence.mapper;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import emazon.stock.domain.model.Category;
import emazon.stock.ports.persistence.entity.CategoryEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
class ICategoryEntityMapperTest {
    private final ICategoryEntityMapper mapper = Mappers.getMapper(ICategoryEntityMapper.class);

    @Test
    void shouldMapToEntity() {
        // Arrange
        Category category = new Category(null, "Category Name", "A description");

        // Act
        CategoryEntity categoryEntity = mapper.toEntity(category);

        // Assert
        assertNull(categoryEntity.getId()); // Id is ignored
        assertEquals(category.getName(), categoryEntity.getName());
        assertEquals(category.getDescription(), categoryEntity.getDescription());
    }

    @Test
    void shouldMapToModel() {
        // Arrange
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1L);
        categoryEntity.setName("Category Name");
        categoryEntity.setDescription("A description");

        // Act
        Category category = mapper.toModel(categoryEntity);

        // Assert
        assertEquals(categoryEntity.getId(), category.getId());
        assertEquals(categoryEntity.getName(), category.getName());
        assertEquals(categoryEntity.getDescription(), category.getDescription());
    }
}
