package emazon.stock.ports.application.mapper.response;

import emazon.stock.domain.model.Category;
import emazon.stock.ports.application.dto.response.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryResponseMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "name")
    CategoryResponse toCategoryResponse(Category category);
    List<CategoryResponse> toCategoryResponses(List<Category> categories);
}