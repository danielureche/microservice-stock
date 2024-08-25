package emazon.stock.ports.persistence.mapper;

import emazon.stock.domain.model.Brand;
import emazon.stock.ports.persistence.entity.BrandEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class IBrandEntityMapperTest {

    private final IBrandEntityMapper mapper = Mappers.getMapper(IBrandEntityMapper.class);

    @Test
    void shouldMapToEntity() {
        // Arrange
        Brand brand = new Brand(null, "Brand Name", "A description");

        // Act
        BrandEntity brandEntity = mapper.toEntity(brand);

        // Assert
        assertNull(brandEntity.getId()); // Id is ignored
        assertEquals(brand.getName(), brandEntity.getName());
        assertEquals(brand.getDescription(), brandEntity.getDescription());
    }

    @Test
    void shouldMapToModel() {
        // Arrange
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(1L);
        brandEntity.setName("Brand Name");
        brandEntity.setDescription("A description");

        // Act
        Brand brand = mapper.toModel(brandEntity);

        // Assert
        assertEquals(brandEntity.getId(), brand.getId());
        assertEquals(brandEntity.getName(), brand.getName());
        assertEquals(brandEntity.getDescription(), brand.getDescription());
    }
}
