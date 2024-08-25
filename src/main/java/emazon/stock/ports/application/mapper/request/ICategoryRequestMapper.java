package emazon.stock.ports.application.mapper.request;

import emazon.stock.domain.model.Category;
import emazon.stock.ports.application.dto.request.CategoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface ICategoryRequestMapper {
    @Mapping(target = "id", ignore = true)
    Category toCategory(CategoryRequest categoryRequest);
}
