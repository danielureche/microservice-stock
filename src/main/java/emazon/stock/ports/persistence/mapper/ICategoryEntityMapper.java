package emazon.stock.ports.persistence.mapper;

import emazon.stock.domain.model.Category;
import emazon.stock.ports.persistence.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface ICategoryEntityMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    CategoryEntity toEntity(Category category);
    Category toModel(CategoryEntity categoryEntity);
    List<Category> toCategoryList(List<CategoryEntity> categoryEntityList);
}
