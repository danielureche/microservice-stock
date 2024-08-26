package emazon.stock.ports.application.mapper.response;

import emazon.stock.domain.model.Category;
import emazon.stock.ports.application.dto.response.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryResponseMapper {
    List<CategoryResponse> toCategoryResponses(List<Category> categories);
}
