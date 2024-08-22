package emazon.stock.ports.persistence.mapper;

import emazon.stock.domain.model.Category;
import emazon.stock.ports.persistence.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface ICategoryEntityMapper {
    CategoryEntity toEntity(Category category);
}
